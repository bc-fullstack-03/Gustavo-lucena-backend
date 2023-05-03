package br.com.bootcam.sysmap.models.dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserPasswordRequest {

    @NotBlank
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres.")
    private String password;
}
