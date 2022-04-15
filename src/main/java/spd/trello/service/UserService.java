package spd.trello.service;

import org.springframework.stereotype.Service;
import spd.trello.domain.User;
import spd.trello.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService extends AbstractService<User, UserRepository> {

    public UserService(UserRepository repository) {
        super(repository);
    }

    public Optional<User> findUserByEmail(String email) {
        return repository.findUserByEmail(email);
    }
}
