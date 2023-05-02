package br.com.bootcam.sysmap.data;

import br.com.bootcam.sysmap.models.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends MongoRepository<User, UUID> {
    List<User> findUserByEmailContainingIgnoreCase(String email);
    Optional<User> findUserByEmail(String email);
    List<User> findUserByIdIn(List<UUID> usersId);
}
