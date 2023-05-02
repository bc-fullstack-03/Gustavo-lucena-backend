package br.com.bootcam.sysmap.models.dtos.user;

import br.com.bootcam.sysmap.models.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserRequest {

    private UUID id;
    private String name;
    private String email;
    private String avatarImgUrl;
    private Integer followers;
    private Integer following;

    public ResponseUserRequest(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.avatarImgUrl = user.getAvatarImgURL();
        this.followers = user.getFollowers() == null ? 0 : user.getFollowers().size();
        this.following = user.getFollowing() == null ? 0 : user.getFollowing().size();
    }
}
