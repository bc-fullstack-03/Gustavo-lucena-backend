package br.com.bootcam.sysmap.models.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

@Data
public class Post {

    @Id
    private UUID id;
    private UUID userId;
    private String content;
    private List<UUID> likes;
    private List<Comment> comments;
}
