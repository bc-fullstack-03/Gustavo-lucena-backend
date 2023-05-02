package br.com.bootcam.sysmap.services.post;


import br.com.bootcam.sysmap.models.dtos.post.RegisterPostRequest;
import br.com.bootcam.sysmap.models.dtos.post.ResponsePostRequest;
import br.com.bootcam.sysmap.models.entities.Post;

import java.util.List;
import java.util.UUID;

public interface IPostService {

    void savePost (Post post);
    String sendPost(RegisterPostRequest request);
    void setLike (String postId);
    List<ResponsePostRequest> findAllPosts();
    String updatePost(RegisterPostRequest request, String postId);
    Post getPostById (String id);
    void deletePost(String postId);
}
