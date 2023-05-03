package br.com.bootcam.sysmap.models.dtos.comments;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCommentRequest {

    @NotBlank
    @Size(min = 2)
    private String content;
}
