package br.com.bootcam.sysmap.services.post;


import br.com.bootcam.sysmap.models.dtos.post.RegisterPostRequest;
import br.com.bootcam.sysmap.models.dtos.post.ResponsePostRequest;
import br.com.bootcam.sysmap.models.dtos.user.ResponseUserRequest;
import br.com.bootcam.sysmap.models.entities.Post;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPostService {

    void save(Post post);
    String sendPost(String content, MultipartFile postFile);
    void setOrUnSetLike (String postId);
    List<ResponsePostRequest> findAllPosts();
    ResponsePostRequest findPostById(String postId);
    List<ResponsePostRequest> findAllPostsFromAnUser(String userId);
    List<ResponsePostRequest> findAllPostsFromFollowingsUser();
    List<ResponseUserRequest> findAllUsersLikedPost(String postId);
    String updatePost(RegisterPostRequest request, String postId);
    Post getPostById (String id);
    void deletePost(String postId);
}
