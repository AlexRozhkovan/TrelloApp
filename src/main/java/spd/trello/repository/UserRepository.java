package spd.trello.repository;

import org.springframework.stereotype.Repository;
import spd.trello.domain.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CommonRepository<User> {
    Optional<User> findUserByEmail(String email);
}
