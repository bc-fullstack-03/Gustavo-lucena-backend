package br.com.bootcam.sysmap.models.dtos.comments;

import br.com.bootcam.sysmap.models.entities.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCommentsRequest {

    private UUID id;
    private String userEmail;
    private String content;

    public ResponseCommentsRequest(Comment comment) {
        this.id = comment.getId();
        this.userEmail = comment.getUserEmail();
        this.content = comment.getContent();
    }
}
