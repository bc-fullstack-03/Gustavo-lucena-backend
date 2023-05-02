package br.com.bootcam.sysmap.api.controllers;

import br.com.bootcam.sysmap.models.dtos.post.RegisterPostRequest;
import br.com.bootcam.sysmap.models.dtos.post.ResponsePostRequest;
import br.com.bootcam.sysmap.services.post.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;
    @PostMapping
    public ResponseEntity<String> sendPost(@Valid @RequestBody RegisterPostRequest request){
        return new ResponseEntity<>(postService.sendPost(request), HttpStatus.CREATED);
    }

    @PostMapping("/like/{postId}")
    public ResponseEntity<Void> setLike(@PathVariable("postId") String postId){
        postService.setLike(postId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ResponsePostRequest>> findAllPosts(){
        return ResponseEntity.ok(postService.findAllPosts());
    }

    @PutMapping("/{postId}")
    public ResponseEntity<String> updatePost(@Valid @RequestBody RegisterPostRequest request, @PathVariable("postId") String postId){
        return  ResponseEntity.ok(postService.updatePost(request, postId));

    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable("postId") String postId){
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

}
