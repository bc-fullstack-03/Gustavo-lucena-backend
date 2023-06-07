package br.com.bootcam.sysmap.services.comment;

import br.com.bootcam.sysmap.models.dtos.comments.RegisterCommentRequest;
import br.com.bootcam.sysmap.models.dtos.comments.ResponseCommentsRequest;
import br.com.bootcam.sysmap.models.entities.Comment;
import br.com.bootcam.sysmap.models.entities.Post;
import br.com.bootcam.sysmap.models.entities.User;
import br.com.bootcam.sysmap.services.auth.AuthenticationService;
import br.com.bootcam.sysmap.services.post.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CommentService implements ICommentService{

    private final PostService postService;

    @Override
    public ResponseCommentsRequest createComment(RegisterCommentRequest request, String postId) {
        User user = AuthenticationService.getLoggedUser();
        Post post = postService.getPostById(postId);

        Comment comment = new Comment(user.getEmail(), request.getContent());

        post.getComments().add(comment);
        postService.save(post);

        return new ResponseCommentsRequest(comment);
    }
    @Override
    public List<ResponseCommentsRequest> listComments(String postId) {
        List<Comment> comments = postService.getPostById(postId).getComments();
        return comments.stream().map(ResponseCommentsRequest::new).toList();
    }

    @Override
    public void deleteComment(String postId, String commentId) {
        User logged = AuthenticationService.getLoggedUser();
        Post post = postService.getPostById(postId);

        post.removeComment(UUID.fromString(commentId), logged.getEmail());

        postService.save(post);
    }
}
