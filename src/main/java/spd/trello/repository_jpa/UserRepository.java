package spd.trello.repository_jpa;

import org.springframework.stereotype.Repository;
import spd.trello.domain.User;

@Repository
public interface UserRepository extends CommonRepository<User> {
}