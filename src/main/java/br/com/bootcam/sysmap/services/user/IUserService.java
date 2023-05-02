package br.com.bootcam.sysmap.services.user;

import br.com.bootcam.sysmap.models.dtos.user.CreateUserRequest;
import br.com.bootcam.sysmap.models.dtos.user.ResponseUserRequest;
import br.com.bootcam.sysmap.models.entities.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    String createUser(CreateUserRequest request);
    List<ResponseUserRequest> findUsers();
    List<ResponseUserRequest> getUsersByEmail(String email);
    List<ResponseUserRequest> getUserFollowersByEmail(String email);
    List<ResponseUserRequest> getUserFollowingByEmail(String email);
    void followAndUnfollow(String email);
    String updateUser(CreateUserRequest request);
    User getUserByEmail(String email);
    User getUserById(UUID id);
    void deleteUser(String email);

}
