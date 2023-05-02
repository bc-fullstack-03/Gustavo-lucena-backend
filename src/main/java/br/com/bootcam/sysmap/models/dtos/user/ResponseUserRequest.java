package br.com.bootcam.sysmap.models.dtos.user;

import br.com.bootcam.sysmap.models.entities.User;
import lombok.Data;

import java.util.UUID;

@Data
public class ResponseUserRequest {

    private UUID id;
    private String name;
    private String email;
    private String avatarImgUrl;
    private Integer followers;
    private Integer following;

    public ResponseUserRequest() {
    }

    public ResponseUserRequest(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.avatarImgUrl = user.getAvatarImgURL();
        this.followers = user.getFollowers() == null ? 0 : user.getFollowers().size();
        this.following = user.getFollowing() == null ? 0 : user.getFollowing().size();
    }
}
