package br.com.bootcam.sysmap.models.entities;

import br.com.bootcam.sysmap.models.dtos.user.CreateUserRequest;
import br.com.bootcam.sysmap.models.dtos.user.UpdateUserRequest;
import br.com.bootcam.sysmap.models.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    private UUID id;
    private String name;
    private String email;
    private String password;
    private String avatarImgURL;
    private List<UUID> followers;
    private List<UUID> following;
    private LocalDateTime createdAt;
    private Role role;

    public User(CreateUserRequest request){
        setId();
        setCreatedAt();
        this.role = Role.USER;
        this.name = request.getName();
        this.email = request.getEmail();
        this.password = request.getPassword();
    }

    protected void setId(){
        this.id = UUID.randomUUID();
    }
    public void setCreatedAt(){
        this.createdAt = LocalDateTime.now();
    }

    public void update(UpdateUserRequest request){
        this.name = request.getName();
        this.email = request.getEmail();
    }

    public void addFollower(UUID idFollower){
        if (followers == null) setFollowers(new ArrayList<>());
        followers.add(idFollower);
    }

    public void addFollowing(UUID idFollowing){
        if (following == null) setFollowing(new ArrayList<>());
        following.add(idFollowing);
    }

    public void removeFollower(UUID idFollower){
        followers.remove(idFollower);
    }

    public void removeFollowing(UUID idFollowing){
        following.remove(idFollowing);
    }

    public boolean followingContains(UUID id){
        if (following == null) setFollowing(new ArrayList<>());
        return following.contains(id);
    }

    public void followOrUnfollow(User followUser){
        if (this.followingContains(followUser.getId())) {
            this.removeFollowing(followUser.getId());
            followUser.removeFollower(this.getId());
        }else {
            this.addFollowing(followUser.getId());
            followUser.addFollower(this.getId());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
