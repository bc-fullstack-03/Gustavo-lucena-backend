package br.com.bootcam.sysmap.services.post;

import br.com.bootcam.sysmap.api.exceptions.MethodNotAllowedException;
import br.com.bootcam.sysmap.api.exceptions.NoAccessException;
import br.com.bootcam.sysmap.api.exceptions.ResourceNotFoundExceptions;
import br.com.bootcam.sysmap.api.exceptions.UploadFileException;
import br.com.bootcam.sysmap.data.PostRepository;
import br.com.bootcam.sysmap.models.dtos.post.RegisterPostRequest;
import br.com.bootcam.sysmap.models.dtos.post.ResponsePostRequest;
import br.com.bootcam.sysmap.models.dtos.user.ResponseUserRequest;
import br.com.bootcam.sysmap.models.entities.Post;
import br.com.bootcam.sysmap.models.entities.User;
import br.com.bootcam.sysmap.services.auth.AuthenticationService;
import br.com.bootcam.sysmap.services.fileupload.IFileUploadService;
import br.com.bootcam.sysmap.services.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PostService implements IPostService{

    private final PostRepository postRepository;
    private final IUserService userService;
    private final IFileUploadService fileUploadService;

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }

    @Override
    public String sendPost(String content, MultipartFile postFile){
        if(content == null && postFile == null){
            throw new MethodNotAllowedException("O post deve ter pelo menos uma imagem ou um texto");
        }

        User loggedUser = AuthenticationService.getLoggedUser();
        Post post = new Post(loggedUser.getId(), content);

        if (postFile != null){
            String fileUrl;

            try {
                fileUrl = fileUploadService.upload(postFile, post.getId());
            }catch (Exception ex){
                throw new UploadFileException("Erro ao fazer upload do arquivo:" + ex.getMessage());
            }

            post.setFileUrl(fileUrl);
        }

        save(post);
        return post.getId().toString();
    }

    @Override
    public void setOrUnSetLike(String postId) {
        Post post = getPostById(postId);
        User user = AuthenticationService.getLoggedUser();

        post.like(user.getId());

        save(post);
    }

    @Override
    public List<ResponsePostRequest> findAllPosts() {
        List<Post> posts = postRepository.findAllPostByOrderByCreatedAtDesc();
        return posts.stream().map(ResponsePostRequest::new).toList();
    }

    @Override
    public List<ResponsePostRequest> findAllPostsFromAnUser(String userId) {
        List<Post> posts = postRepository.findAllPostByUserIdOrderByCreatedAtDesc(UUID.fromString(userId));
        return posts.stream().map(ResponsePostRequest::new).toList();
    }

    @Override
    public List<ResponsePostRequest> findAllPostsFromFollowingsUser() {
        User user = AuthenticationService.getLoggedUser();
        List<Post> posts = postRepository.findPostByUserIdInOrderByCreatedAtDesc(user.getFollowing());
        return posts.stream().map(ResponsePostRequest::new).toList();
    }

    @Override
    public List<ResponseUserRequest> findAllUsersLikedPost(String postId) {
        Post post = getPostById(postId);
        return userService.findUsersByIds(post.getLikes());
    }

    @Override
    public String updatePost(RegisterPostRequest request, String postId) {
        Post postForUpdate = getPostById(postId);
        User userPost = userService.getUserById(postForUpdate.getUserId());
        User logged = AuthenticationService.getLoggedUser();

        if(!userPost.equals(logged)) throw new NoAccessException("Usuário não autorizado.");

        postForUpdate.setContent(request.getContent());
        save(postForUpdate);

        return postForUpdate.getUserId().toString();
    }

    @Override
    public Post getPostById (String id){
        return postRepository.findById(UUID.fromString(id)).orElseThrow(
                () -> new ResourceNotFoundExceptions("Post não encontrado")
        );
    }

    @Override
    public void deletePost(String postId) {
        User logged = AuthenticationService.getLoggedUser();
        Post post = getPostById(postId);

        post.isValidToDelete(logged.getId());

        postRepository.delete(post);
    }
}