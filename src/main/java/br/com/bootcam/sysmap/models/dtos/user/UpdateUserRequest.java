package br.com.bootcam.sysmap.models.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    @NotBlank
    @Size(min = 3, max = 255, message = "Nome deve ter no mínimo 3 letras")
    private String name;
    @NotBlank
    @Email(message = "O email deve ser válido")
    @Size(min = 6, message = "O email deve ter mais que 6 caracteres")
    private String email;
}
