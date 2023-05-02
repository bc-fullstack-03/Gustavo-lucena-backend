package br.com.bootcam.sysmap.models.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    private UUID id;
    private String userEmail;
    private String content;

    public Comment(String userEmail, String content) {
        id = UUID.randomUUID();
        this.userEmail = userEmail;
        this.content = content;
    }
}
