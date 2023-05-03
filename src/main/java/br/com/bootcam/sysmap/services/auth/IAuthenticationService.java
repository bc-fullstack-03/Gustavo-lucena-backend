package br.com.bootcam.sysmap.services.auth;

import br.com.bootcam.sysmap.models.dtos.auth.AuthenticationRequest;
import br.com.bootcam.sysmap.models.dtos.auth.AuthenticationResponse;
import br.com.bootcam.sysmap.models.dtos.user.CreateUserRequest;
import br.com.bootcam.sysmap.models.dtos.user.ResponseUserRequest;
import br.com.bootcam.sysmap.models.dtos.user.UpdateUserPasswordRequest;

public interface IAuthenticationService {
    AuthenticationResponse register(CreateUserRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    ResponseUserRequest responseLoggedUser();
    void updateUserPassword(UpdateUserPasswordRequest request);
}
