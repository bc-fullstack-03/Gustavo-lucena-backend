package br.com.bootcam.sysmap.models.entities;

import br.com.bootcam.sysmap.models.dtos.user.CreateUserRequest;
import br.com.bootcam.sysmap.models.enums.Role;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
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

    public User() {
    }

    public User(String name, String email, String password) {
        setId();
        this.role = Role.USER;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(CreateUserRequest request){
        setId();
        this.role = Role.USER;
        this.name = request.getName();
        this.email = request.getEmail();
        this.password = request.getPassword();
    }

    protected void setId(){
        this.id = UUID.randomUUID();
    }

    public UUID getId(){
        return this.id;
    }

    public void update(CreateUserRequest request){
        this.name = request.getName();
        this.email = request.getEmail();
        this.password = request.getPassword();
    }

    public void setCreatedAt(){
        this.createdAt = LocalDateTime.now();
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
