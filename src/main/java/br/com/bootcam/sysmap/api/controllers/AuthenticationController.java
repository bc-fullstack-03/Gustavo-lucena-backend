package br.com.bootcam.sysmap.api.controllers;

import br.com.bootcam.sysmap.models.dtos.auth.AuthenticationRequest;
import br.com.bootcam.sysmap.models.dtos.auth.AuthenticationResponse;
import br.com.bootcam.sysmap.models.dtos.user.CreateUserRequest;
import br.com.bootcam.sysmap.models.dtos.user.ResponseUserRequest;
import br.com.bootcam.sysmap.services.auth.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final IAuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody CreateUserRequest request) {
        return new  ResponseEntity<>(service.register(request), HttpStatus.CREATED);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @GetMapping("get-logged")
    public ResponseEntity<ResponseUserRequest> getLoggedUser(){
        return ResponseEntity.ok(service.responseLoggedUser());
    }

}
