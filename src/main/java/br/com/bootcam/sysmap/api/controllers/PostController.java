package br.com.bootcam.sysmap.api.controllers;

import br.com.bootcam.sysmap.models.dtos.post.RegisterPostRequest;
import br.com.bootcam.sysmap.models.dtos.post.ResponsePostRequest;
import br.com.bootcam.sysmap.services.post.PostService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Register a new post")
    @PostMapping
    public ResponseEntity<String> sendPost(@Valid @RequestBody RegisterPostRequest request){
        return new ResponseEntity<>(postService.sendPost(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Register a like of the logged user in na post")
    @PostMapping("/like/{postId}")
    public ResponseEntity<Void> setLike(@PathVariable("postId") String postId){
        postService.setLike(postId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Return all posts")
    @GetMapping
    public ResponseEntity<List<ResponsePostRequest>> findAllPosts(){
        return ResponseEntity.ok(postService.findAllPosts());
    }

    @Operation(summary = "Return all posts from an user")
    @GetMapping("/{userId}")
    public ResponseEntity<List<ResponsePostRequest>> findAllPostsFromAnUser(@RequestParam("userId") String userId){
        return ResponseEntity.ok(postService.findAllPostsFromAnUser(userId));
    }

    @Operation(summary = "Update a post")
    @PutMapping("/{postId}")
    public ResponseEntity<String> updatePost(@Valid @RequestBody RegisterPostRequest request, @PathVariable("postId") String postId){
        return  ResponseEntity.ok(postService.updatePost(request, postId));

    }

    @Operation(summary = "Delete a post")
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable("postId") String postId){
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

}
