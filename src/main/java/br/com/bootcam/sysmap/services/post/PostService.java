package br.com.bootcam.sysmap.services.post;

import br.com.bootcam.sysmap.api.exceptions.NoAccessException;
import br.com.bootcam.sysmap.api.exceptions.ResourceNotFoundExceptions;
import br.com.bootcam.sysmap.data.PostRepository;
import br.com.bootcam.sysmap.models.dtos.post.RegisterPostRequest;
import br.com.bootcam.sysmap.models.dtos.post.ResponsePostRequest;
import br.com.bootcam.sysmap.models.entities.Post;
import br.com.bootcam.sysmap.models.entities.User;
import br.com.bootcam.sysmap.services.auth.AuthenticationService;
import br.com.bootcam.sysmap.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PostService implements IPostService{

    private final PostRepository postRepository;
    private final UserService userService;

    public String sendPost(RegisterPostRequest request){
        User logged = AuthenticationService.getLoggedUser();
        Post post = new Post(logged.getId(), request.getContent());

        postRepository.save(post);

        return post.getId().toString();
    }

    @Override
    public List<ResponsePostRequest> findAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(ResponsePostRequest::new).toList();
    }

    @Override
    public String updatePost(RegisterPostRequest request, String postId) {
        Post postForUpdate = getPostById(UUID.fromString(postId));
        User userPost = userService.getUserById(postForUpdate.getUserId());
        User logged = AuthenticationService.getLoggedUser();

        if(!userPost.equals(logged)) throw new NoAccessException("Usuário não autorizado.");

        postForUpdate.setContent(request.getContent());
        postRepository.save(postForUpdate);

        return postForUpdate.getUserId().toString();
    }

    @Override
    public Post getPostById (UUID id){
        return postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundExceptions("Post não encontrado")
        );
    }

    @Override
    public void deletePost(String postId) {
        postRepository.deleteById(UUID.fromString(postId));
    }
}
