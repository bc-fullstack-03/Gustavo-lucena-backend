package br.com.bootcam.sysmap.models.dtos.post;

import br.com.bootcam.sysmap.models.entities.Comment;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ResponsePostRequest {

    private UUID id;
    private UUID userId;
    private String content;
    private List<UUID> likes;
    private List<Comment> comments;
}
