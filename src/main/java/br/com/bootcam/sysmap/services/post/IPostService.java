package br.com.bootcam.sysmap.services.post;


import br.com.bootcam.sysmap.models.dtos.post.RegisterPostRequest;
import br.com.bootcam.sysmap.models.dtos.post.ResponsePostRequest;
import br.com.bootcam.sysmap.models.entities.Post;

import java.util.List;

public interface IPostService {

    void savePost (Post post);
    String sendPost(RegisterPostRequest request);
    void setLike (String postId);
    List<ResponsePostRequest> findAllPosts();
    List<ResponsePostRequest> findAllPostsFromAnUser(String userId);
    String updatePost(RegisterPostRequest request, String postId);
    Post getPostById (String id);
    List<ResponsePostRequest>  getPostsByUserId (String userId);
    void deletePost(String postId);
}
