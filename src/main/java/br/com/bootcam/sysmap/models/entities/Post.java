package br.com.bootcam.sysmap.models.entities;

import br.com.bootcam.sysmap.api.exceptions.MethodNotAllowedException;
import br.com.bootcam.sysmap.api.exceptions.NoAccessException;
import br.com.bootcam.sysmap.api.exceptions.ResourceNotFoundExceptions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    private UUID id;
    private UUID userId;
    private String userEmail;
    private String content;
    private String fileUrl;
    private List<UUID> likes;
    private List<Comment> comments;
    private LocalDateTime createdAt;

    public Post(UUID userId, String userEmail , String content) {
        setId();
        setCreatedAt();
        this.userId = userId;
        this.userEmail = userEmail;
        this.content = content;
    }

    public void setId() {
        this.id = UUID.randomUUID();
    }

    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    public void like(UUID userLikeId){
        if(getLikes() == null) setLikes(new ArrayList<>());

        if(getLikes().contains(userLikeId)){
            getLikes().remove(userLikeId);
        }else {
            getLikes().add(userLikeId);
        }
    }

    public void isAuthorized (UUID userId){
        if(!this.userId.equals(userId)) {
            throw new NoAccessException("Usuário não autorizado.");
        }
    }

    public void removeComment(UUID commentId, String loggedUserEmail){
        Comment comment = getComments().stream().filter(x -> x.getId().equals(commentId)).findAny()
                .orElseThrow(() -> new ResourceNotFoundExceptions("Commentario não encontrado"));

        if(!comment.getUserEmail().equals(loggedUserEmail)){
            throw new MethodNotAllowedException("Você não pode deletar comentarios de outros usuários");
        }

        getComments().remove(comment);
    }
}
