package br.com.bootcam.sysmap.services.post;


import br.com.bootcam.sysmap.models.dtos.post.RegisterPostRequest;
import br.com.bootcam.sysmap.models.dtos.post.ResponsePostRequest;
import br.com.bootcam.sysmap.models.entities.Post;

import java.util.List;
import java.util.UUID;

public interface IPostService {

    String sendPost(RegisterPostRequest request);
    List<ResponsePostRequest> findAllPosts();
    String updatePost(RegisterPostRequest request, String postId);
    Post getPostById (UUID id);

    void deletePost(String postId);
}
