package br.com.bootcam.sysmap.models.dtos.user;

import lombok.Data;

@Data
public class CreateUserRequest {

    private String name;
    private String email;
    private String password;
}
