package br.com.bootcam.sysmap.api.controllers;

import br.com.bootcam.sysmap.models.dtos.user.CreateUserRequest;
import br.com.bootcam.sysmap.models.dtos.user.ResponseUserRequest;
import br.com.bootcam.sysmap.services.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    final private IUserService userService;

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest request){
        String userId = userService.createUser(request);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(userId).toUri();

        return ResponseEntity.created(uri).body(userId);
//        return new ResponseEntity<>(userId ,HttpStatus.CREATED);
    }

    @PostMapping("/follow/{emailFollow}")
    public ResponseEntity<Void> followAndUnfollow(@PathVariable("emailFollow") String emailFollow){
        userService.followAndUnfollow(emailFollow );
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ResponseUserRequest>> findUsers(){
        List<ResponseUserRequest> response = userService.findUsers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<ResponseUserRequest>> getUserByEmail(@PathVariable("email") String email){
        List<ResponseUserRequest> response = userService.getUsersByEmail(email);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{email}/followers")
    public ResponseEntity<List<ResponseUserRequest>> getUserFollowersByEmail(@PathVariable("email") String email){
        return ResponseEntity.ok(userService.getUserFollowersByEmail(email));
    }

    @GetMapping("/{email}/following")
    public ResponseEntity<List<ResponseUserRequest>> getUserFollowingByEmail(@PathVariable("email") String email){
        return ResponseEntity.ok(userService.getUserFollowingByEmail(email));
    }

    @PutMapping
    public ResponseEntity<String> updateUser (@RequestBody CreateUserRequest request){
        String id = userService.updateUser(request);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @DeleteMapping("/{email}")
    public  ResponseEntity<Void> deleteUser(@PathVariable("email") String email){
        userService.deleteUser(email);
        return ResponseEntity.noContent().build();
    }
}
