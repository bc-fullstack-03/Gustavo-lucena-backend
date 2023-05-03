package br.com.bootcam.sysmap.services.auth;

import br.com.bootcam.sysmap.config.security.JwtService;
import br.com.bootcam.sysmap.models.dtos.auth.AuthenticationRequest;
import br.com.bootcam.sysmap.models.dtos.auth.AuthenticationResponse;
import br.com.bootcam.sysmap.models.dtos.user.CreateUserRequest;
import br.com.bootcam.sysmap.models.dtos.user.ResponseUserRequest;
import br.com.bootcam.sysmap.models.dtos.user.UpdateUserPasswordRequest;
import br.com.bootcam.sysmap.models.entities.User;
import br.com.bootcam.sysmap.services.user.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService{

    private final IUserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;

    public AuthenticationResponse register(CreateUserRequest request) {
        request.setPassword(encode(request.getPassword()));
        userService.createUser(request);

        User user = objectMapper.convertValue(request, User.class);

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userService.getUserByEmail(request.getEmail());
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public ResponseUserRequest responseLoggedUser() {
        return new ResponseUserRequest(getLoggedUser());
    }

    @Override
    public void updateUserPassword(UpdateUserPasswordRequest request) {
        User loggedUser = AuthenticationService.getLoggedUser();
        loggedUser.setPassword(encode(request.getPassword()));

        userService.save(loggedUser);
    }

    public static User getLoggedUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public String encode(String encoder){
        return passwordEncoder.encode(encoder);
    }
}