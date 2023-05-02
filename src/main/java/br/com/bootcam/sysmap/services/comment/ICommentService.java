package br.com.bootcam.sysmap.services.comment;

import br.com.bootcam.sysmap.models.dtos.comments.RegisterCommentRequest;
import br.com.bootcam.sysmap.models.dtos.comments.ResponseCommentsRequest;

import java.util.List;

public interface ICommentService {

    ResponseCommentsRequest createComment(RegisterCommentRequest request, String postId);
    List<ResponseCommentsRequest> listComments(String postId);
    void deleteComment(String commentId, String postId);
}
