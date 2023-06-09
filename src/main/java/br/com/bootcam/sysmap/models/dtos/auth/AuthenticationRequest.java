package br.com.bootcam.sysmap.models.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {


    @NotBlank
    @Email(message = "O email deve ser válido")
    private String email;
    @NotBlank
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres.")
    private String password;
}