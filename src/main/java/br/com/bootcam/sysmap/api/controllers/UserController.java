package br.com.bootcam.sysmap.api.controllers;

import br.com.bootcam.sysmap.models.dtos.user.ResponseUserRequest;
import br.com.bootcam.sysmap.models.dtos.user.UpdateUserRequest;
import br.com.bootcam.sysmap.services.user.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    final private IUserService userService;

    @Operation(summary = "Follow or Unfollow a user by email")
    @PostMapping("/follow/{emailFollow}")
    public ResponseEntity<Void> followAndUnfollow(@PathVariable("emailFollow") String emailFollow){
        userService.followOrUnfollow(emailFollow );
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Upload a photo for the profile user")
    @PostMapping(value = "/avartaImg", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> registerAvatarImg(@RequestPart MultipartFile avatarImg){
        return ResponseEntity.ok(userService.registerAvatarImg(avatarImg));
    }

    @Operation(summary = "Return a list of all profiles users")
    @GetMapping
    public ResponseEntity<List<ResponseUserRequest>> findUsers(){
        List<ResponseUserRequest> response = userService.findUsers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Search users by email")
    @GetMapping("/{email}")
    public ResponseEntity<List<ResponseUserRequest>> findUsersByEmail(@PathVariable("email") String email){
        List<ResponseUserRequest> response = userService.findUsersByEmail(email);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Return users by id")
    @GetMapping("/byId/{userId}")
    public ResponseEntity<ResponseUserRequest> findUserById(@PathVariable("userId") String userId){
        ResponseUserRequest response = userService.findUserById(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Return all followers profiles from an user")
    @GetMapping("/{email}/followers")
    public ResponseEntity<List<ResponseUserRequest>> findUserFollowersByEmail(@PathVariable("email") String email){
        return ResponseEntity.ok(userService.findUserFollowersByEmail(email));
    }

    @Operation(summary = "Return all following profiles from an user")
    @GetMapping("/{email}/following")
    public ResponseEntity<List<ResponseUserRequest>> findUserFollowingByEmail(@PathVariable("email") String email){
        return ResponseEntity.ok(userService.findUserFollowingByEmail(email));
    }

    @Operation(summary = "Update profile of the logged user")
    @PutMapping
    public ResponseEntity<String> updateUser (@Valid @RequestBody UpdateUserRequest request){
        String id = userService.updateUser(request);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @Operation(summary = "Delete account of the logged user")
    @DeleteMapping("/delete")
    public  ResponseEntity<Void> deleteUser(){
        userService.deleteUser();
        return ResponseEntity.noContent().build();
    }
}
