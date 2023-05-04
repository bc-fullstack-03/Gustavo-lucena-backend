package br.com.bootcam.sysmap.services.user;

import br.com.bootcam.sysmap.api.exceptions.MethodNotAllowedException;
import br.com.bootcam.sysmap.api.exceptions.ResourceNotFoundExceptions;
import br.com.bootcam.sysmap.api.exceptions.UploadFileException;
import br.com.bootcam.sysmap.api.exceptions.UserAlreadyExistsException;
import br.com.bootcam.sysmap.data.UserRepository;
import br.com.bootcam.sysmap.models.dtos.user.CreateUserRequest;
import br.com.bootcam.sysmap.models.dtos.user.ResponseUserRequest;
import br.com.bootcam.sysmap.models.dtos.user.UpdateUserRequest;
import br.com.bootcam.sysmap.models.entities.User;
import br.com.bootcam.sysmap.services.auth.AuthenticationService;
import br.com.bootcam.sysmap.services.fileUpload.IFileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final IFileUploadService fileUploadService;


    @Override
    public void createUser(CreateUserRequest request) {
        userRepository.findUserByEmail(request.getEmail()).ifPresent(s -> {
            throw new UserAlreadyExistsException("Este email de usuário já existe");
        });

        User user = new User(request);
        user.setCreatedAt();

        save(user);
    }

    @Override
    public String registerAvatarImg(MultipartFile avatarImg) {
        User user = AuthenticationService.getLoggedUser();

        String avatarImgUri = "";

        try {
            String fileName = user.getId() + "." + avatarImg.getOriginalFilename().substring(avatarImg.getOriginalFilename().lastIndexOf(".")+ 1);

            avatarImgUri = fileUploadService.upload(avatarImg, fileName);
        }catch (Exception ex){
            throw new UploadFileException("Erro ao fazer upload de imagem");
        }

        user.setAvatarImgURL(avatarImgUri);
        save(user);

        return null;
    }

    @Override
    public List<ResponseUserRequest> findUsers() {
        List<User> usersRequest = userRepository.findAll();

        return usersRequest.stream().map(ResponseUserRequest::new).toList();
    }

    public List<ResponseUserRequest> findUsersByEmail(String email) {
        List<User> usersRequest = userRepository.findUserByEmailContainingIgnoreCase(email);
        return usersRequest.stream().map(ResponseUserRequest::new).toList();
    }

    @Override
    public List<ResponseUserRequest> findUserFollowersByEmail(String email) {
        User user = getUserByEmail(email);
        List<User> followers = userRepository.findUserByIdIn(user.getFollowers());

        return followers.stream().map(ResponseUserRequest::new).toList();
    }

    @Override
    public List<ResponseUserRequest> findUserFollowingByEmail(String email) {
        User user = getUserByEmail(email);
        List<User> followers = userRepository.findUserByIdIn(user.getFollowing());

        return followers.stream().map(ResponseUserRequest::new).toList();
    }

    public void followOrUnfollow(String email) {
        User logged = AuthenticationService.getLoggedUser();
        User followUser = getUserByEmail(email);

        if (logged.getId().equals(followUser.getId())) throw new MethodNotAllowedException("Você não pode seguir se mesmo(a)");

        logged.followOrUnfollow(followUser);

        save(logged);
        save(followUser);
    }

    @Override
    public String updateUser(UpdateUserRequest request) {
        User loggedUser = AuthenticationService.getLoggedUser();
        loggedUser.update(request);

        userRepository.save(loggedUser);
        return loggedUser.getId().toString();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(
                () -> new ResourceNotFoundExceptions("User not found with email " + email)
        );
    }

    @Override
    public ResponseUserRequest findUserById(String userId) {
        return new ResponseUserRequest(getUserById(UUID.fromString(userId)));
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundExceptions("User not found")
        );
    }

    @Override
    public void deleteUser() {
        User user = AuthenticationService.getLoggedUser();
        userRepository.delete(user);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}