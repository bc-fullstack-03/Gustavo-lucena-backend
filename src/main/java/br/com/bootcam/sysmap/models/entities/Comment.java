package br.com.bootcam.sysmap.models.entities;

import lombok.Data;

import java.util.UUID;

@Data
public class Comment {

    private UUID userId;
    private String content;
}
