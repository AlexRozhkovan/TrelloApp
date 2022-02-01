package spd.trello.repository;

import org.springframework.stereotype.Repository;
import spd.trello.domain.User;

import java.util.List;

@Repository
public interface UserRepository extends IRepository<User> {

    List<User> findByFirstName(String firstName);

}
