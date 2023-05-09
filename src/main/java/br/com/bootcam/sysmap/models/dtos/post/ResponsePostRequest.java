package br.com.bootcam.sysmap.models.dtos.post;

import br.com.bootcam.sysmap.models.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponsePostRequest {

    private UUID id;
    private UUID userId;
    private String content;
    private String fileUrl;
    private Integer likes;
    private Integer comments;
    private LocalDateTime createdAt;

    public ResponsePostRequest(Post post) {
        this.id = post.getId();
        this.userId = post.getUserId();
        this.content = post.getContent();
        this.fileUrl = post.getFileUrl();
        this.likes = post.getLikes() == null ? 0 : post.getLikes().size();
        this.comments = post.getComments() == null ? 0 : post.getComments().size();
        this.createdAt = post.getCreatedAt();
    }
}
