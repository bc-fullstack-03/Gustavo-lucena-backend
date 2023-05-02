package br.com.bootcam.sysmap.models.dtos.post;

import lombok.Data;

import java.util.UUID;

@Data
public class CreatePostRequest {

    private UUID userId;
    private String content;
}
