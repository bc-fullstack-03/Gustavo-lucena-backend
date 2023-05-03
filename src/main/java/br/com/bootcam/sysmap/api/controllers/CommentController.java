package br.com.bootcam.sysmap.api.controllers;

import br.com.bootcam.sysmap.models.dtos.comments.RegisterCommentRequest;
import br.com.bootcam.sysmap.models.dtos.comments.ResponseCommentsRequest;
import br.com.bootcam.sysmap.services.comment.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "Create a new comment in a post")
    @PostMapping("/{postId}")
    public ResponseEntity<ResponseCommentsRequest> createComment
            (@Valid @RequestBody RegisterCommentRequest request, @PathVariable("postId") String postId){
        return new ResponseEntity<>(commentService.createComment(request, postId), HttpStatus.CREATED);
    }

    @Operation(summary = "Return a comment list from a post")
    @GetMapping("/{postId}")
    public ResponseEntity<List<ResponseCommentsRequest>> listComments(@PathVariable("postId") String postId){
        return ResponseEntity.ok(commentService.listComments(postId));
    }

    @Operation(summary = "Delete a logged user comment in a post")
    @DeleteMapping("/{postId}/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("postId") String postId, @PathVariable("commentId") String commentId){
        commentService.deleteComment(postId, commentId);
        return ResponseEntity.noContent().build();
    }
}
