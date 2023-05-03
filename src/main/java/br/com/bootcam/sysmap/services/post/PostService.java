package br.com.bootcam.sysmap.services.post;

import br.com.bootcam.sysmap.api.exceptions.NoAccessException;
import br.com.bootcam.sysmap.api.exceptions.ResourceNotFoundExceptions;
import br.com.bootcam.sysmap.data.PostRepository;
import br.com.bootcam.sysmap.models.dtos.post.RegisterPostRequest;
import br.com.bootcam.sysmap.models.dtos.post.ResponsePostRequest;
import br.com.bootcam.sysmap.models.entities.Post;
import br.com.bootcam.sysmap.models.entities.User;
import br.com.bootcam.sysmap.services.auth.AuthenticationService;
import br.com.bootcam.sysmap.services.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PostService implements IPostService{

    private final PostRepository postRepository;
    private final IUserService userService;

    @Override
    public void savePost(Post post) {
        postRepository.save(post);
    }

    public String sendPost(RegisterPostRequest request){
        User logged = AuthenticationService.getLoggedUser();
        Post post = new Post(logged.getId(), request.getContent());

        savePost(post);

        return post.getId().toString();
    }

    @Override
    public void setLike(String postId) {
        Post post = getPostById(postId);
        User user = AuthenticationService.getLoggedUser();

        if(post.getLikes() == null) post.setLikes(new ArrayList<>());
        if(post.getLikes().contains(user.getId())){
            post.getLikes().remove(user.getId());
        }else {
            post.getLikes().add(user.getId());
        }

        savePost(post);
    }

    @Override
    public List<ResponsePostRequest> findAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(ResponsePostRequest::new).toList();
    }

    @Override
    public List<ResponsePostRequest> findAllPostsFromAnUser(String userId) {
        List<Post> posts = postRepository.findAllPostByUserId(UUID.fromString(userId));
        return posts.stream().map(ResponsePostRequest::new).toList();
    }

    @Override
    public String updatePost(RegisterPostRequest request, String postId) {
        Post postForUpdate = getPostById(postId);
        User userPost = userService.getUserById(postForUpdate.getUserId());
        User logged = AuthenticationService.getLoggedUser();

        if(!userPost.equals(logged)) throw new NoAccessException("Usuário não autorizado.");

        postForUpdate.setContent(request.getContent());
        savePost(postForUpdate);

        return postForUpdate.getUserId().toString();
    }

    @Override
    public Post getPostById (String id){
        return postRepository.findById(UUID.fromString(id)).orElseThrow(
                () -> new ResourceNotFoundExceptions("Post não encontrado")
        );
    }

    @Override
    public List<ResponsePostRequest>  getPostsByUserId (String userId){
        List<Post> posts = postRepository.findAllPostByUserId(UUID.fromString(userId));
        return posts.stream().map(ResponsePostRequest::new).toList();
    }

    @Override
    public void deletePost(String postId) {
        postRepository.deleteById(UUID.fromString(postId));
    }


}
