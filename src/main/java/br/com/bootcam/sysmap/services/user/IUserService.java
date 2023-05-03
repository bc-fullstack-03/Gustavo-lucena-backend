package br.com.bootcam.sysmap.services.user;

import br.com.bootcam.sysmap.models.dtos.user.CreateUserRequest;
import br.com.bootcam.sysmap.models.dtos.user.ResponseUserRequest;
import br.com.bootcam.sysmap.models.dtos.user.UpdateUserRequest;
import br.com.bootcam.sysmap.models.entities.User;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    void createUser(CreateUserRequest request);
    List<ResponseUserRequest> findUsers();
    List<ResponseUserRequest> findUsersByEmail(String email);
    List<ResponseUserRequest> findUserFollowersByEmail(String email);
    List<ResponseUserRequest> findUserFollowingByEmail(String email);
    void followOrUnfollow(String email);
    String updateUser(UpdateUserRequest request);
    User getUserByEmail(String email);
    ResponseUserRequest findUserById(String userId);
    User getUserById(UUID id);
    void deleteUser();

    void save(User loggedUser);
}
