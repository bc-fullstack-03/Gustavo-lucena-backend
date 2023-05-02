package br.com.bootcam.sysmap.api.controllers;

import br.com.bootcam.sysmap.models.dtos.post.CreatePostRequest;
import br.com.bootcam.sysmap.models.dtos.post.ResponsePostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/post")
public class PostController {

    @PostMapping
    public ResponseEntity<ResponsePostRequest> sendPost(@RequestBody CreatePostRequest request){
        return null;
    }

}
