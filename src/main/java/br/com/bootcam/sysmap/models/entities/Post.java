package br.com.bootcam.sysmap.models.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    private UUID id;
    private UUID userId;
    private String content;
    private List<UUID> likes;
    private List<Comment> comments;

    public Post(UUID userId, String content) {
        setId();
        this.userId = userId;
        this.content = content;
    }

    public void setId() {
        this.id = UUID.randomUUID();
    }
}
