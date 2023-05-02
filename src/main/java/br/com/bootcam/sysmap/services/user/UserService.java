package br.com.bootcam.sysmap.services.user;

import br.com.bootcam.sysmap.data.UserRepository;
import br.com.bootcam.sysmap.models.dtos.user.CreateUserRequest;
import br.com.bootcam.sysmap.models.dtos.user.ResponseUserRequest;
import br.com.bootcam.sysmap.models.entities.User;
import br.com.bootcam.sysmap.services.auth.AuthenticationService;
import br.com.bootcam.sysmap.api.exceptions.ResourceNotFoundExceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService implements IUserService{

    private final UserRepository userRepository;

    @Override
    public String createUser(CreateUserRequest request){
        User user = new User(request);
        user.setCreatedAt();

        userRepository.save(user);

        return user.getId().toString();
    }

    @Override
    public List<ResponseUserRequest> findUsers() {
        List<User> usersRequest = userRepository.findAll();

        return usersRequest.stream().map(ResponseUserRequest::new).toList();
    }

    public List<ResponseUserRequest> getUsersByEmail(String email){
        List<User> usersRequest = userRepository.findUserByEmailContainingIgnoreCase(email);
        return usersRequest.stream().map(ResponseUserRequest::new).toList();
    }

    @Override
    public List<ResponseUserRequest> getUserFollowersByEmail(String email){
        User user = getUserByEmail(email);
        List<User> followers = userRepository.findUserByIdIn(user.getFollowers());

        return followers.stream().map(ResponseUserRequest::new).toList();
    }

    @Override
    public List<ResponseUserRequest> getUserFollowingByEmail(String email) {
        User user = getUserByEmail(email);
        List<User> followers = userRepository.findUserByIdIn(user.getFollowing());

        return followers.stream().map(ResponseUserRequest::new).toList();
    }

    public void followAndUnfollow(String email){
        User logged = AuthenticationService.getLoggedUser();
        User followUser = getUserByEmail(email);

        if (logged.getFollowing() == null) logged.setFollowing(new ArrayList<>());
        if (followUser.getFollowers() == null) followUser.setFollowers(new ArrayList<>());

        if (logged.getFollowing().stream().anyMatch(x -> followUser.getId().equals(x))) {
            logged.getFollowing().remove(followUser.getId());
            followUser.getFollowers().remove(logged.getId());
        }else {
            logged.getFollowing().add(followUser.getId());
            followUser.getFollowers().add(logged.getId());
        }

        userRepository.save(logged);
        userRepository.save(followUser);
    }

    @Override
    public String updateUser(CreateUserRequest request){
        User loggedUser = getUserByEmail(request.getEmail());
        loggedUser.update(request);

        userRepository.save(loggedUser);
        return loggedUser.getId().toString();
    }

    @Override
    public User getUserByEmail(String email){
        return userRepository.findUserByEmail(email).orElseThrow(
                () -> new ResourceNotFoundExceptions("User not found with email " + email)
        );
    }

    @Override
    public User getUserById(UUID id){
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundExceptions("User not found")
        );
    }

    @Override
    public void deleteUser(String email){
        User user = getUserByEmail(email);
        userRepository.delete(user);
    }
}
