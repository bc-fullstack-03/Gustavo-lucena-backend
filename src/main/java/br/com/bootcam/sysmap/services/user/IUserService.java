package br.com.bootcam.sysmap.services.user;

import br.com.bootcam.sysmap.models.dtos.user.CreateUserRequest;
import br.com.bootcam.sysmap.models.dtos.user.ResponseUserRequest;
import br.com.bootcam.sysmap.models.dtos.user.UpdateUserRequest;
import br.com.bootcam.sysmap.models.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    void createUser(CreateUserRequest request);
    String registerAvatarImg(MultipartFile avatarImg);
    List<ResponseUserRequest> findUsers();
    List<ResponseUserRequest> findUsersByEmail(String email);
    List<ResponseUserRequest> findUserFollowersByEmail(String email);
    List<ResponseUserRequest> findUserFollowingByEmail(String email);
    List<ResponseUserRequest> findUsersByIds(List<UUID> uuids);
    void follow(String email);
    void unfollow(String email);
    String updateUser(UpdateUserRequest request);
    User getUserByEmail(String email);
    ResponseUserRequest findUserById(String userId);
    User getUserById(UUID id);
    void deleteUser();
    void save(User loggedUser);
}
