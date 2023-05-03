package br.com.bootcam.sysmap.api.controllers;

import br.com.bootcam.sysmap.models.dtos.auth.AuthenticationRequest;
import br.com.bootcam.sysmap.models.dtos.auth.AuthenticationResponse;
import br.com.bootcam.sysmap.models.dtos.user.CreateUserRequest;
import br.com.bootcam.sysmap.models.dtos.user.ResponseUserRequest;
import br.com.bootcam.sysmap.models.dtos.user.UpdateUserPasswordRequest;
import br.com.bootcam.sysmap.services.auth.IAuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final IAuthenticationService service;

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody CreateUserRequest request) {
        return new  ResponseEntity<>(service.register(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Authenticate an user")
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @Operation(summary = "Return the logged user")
    @GetMapping("get-logged")
    public ResponseEntity<ResponseUserRequest> getLoggedUser(){
        return ResponseEntity.ok(service.responseLoggedUser());
    }

    @Operation(summary = "Update the password of the logged user")
    @PutMapping("/password")
    public ResponseEntity<String> updateUserPassword (@Valid @RequestBody UpdateUserPasswordRequest request){
        service.updateUserPassword(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
