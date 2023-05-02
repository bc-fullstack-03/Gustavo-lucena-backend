package br.com.bootcam.sysmap.models.dtos.comments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCommentRequest {

    private String content;
}
