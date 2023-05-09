package br.com.bootcam.sysmap.data;

import br.com.bootcam.sysmap.models.entities.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends MongoRepository<Post, UUID> {

    List<Post> findAllPostByOrderByCreatedAtDesc();
    List<Post> findAllPostByUserIdOrderByCreatedAtDesc(UUID id);
    List<Post> findPostByUserIdInOrderByCreatedAtDesc(List<UUID> usersId);
}
