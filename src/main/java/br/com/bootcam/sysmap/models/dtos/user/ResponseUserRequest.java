package br.com.bootcam.sysmap.models.dtos.user;

import br.com.bootcam.sysmap.models.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserRequest {

    private UUID id;
    private String name;
    private String email;
    private String avatarImgUrl;
    private List<UUID> followers;
    private List<UUID> following;

    public ResponseUserRequest(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.avatarImgUrl = user.getAvatarImgURL();
        this.followers = user.getFollowers() == null ? new ArrayList<>() : user.getFollowers();
        this.following = user.getFollowing() == null ? new ArrayList<>() : user.getFollowing();
    }
}
