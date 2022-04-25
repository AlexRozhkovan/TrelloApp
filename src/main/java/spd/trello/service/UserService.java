package spd.trello.service;

import org.springframework.stereotype.Service;
import spd.trello.domain.User;
import spd.trello.repository_jpa.UserRepository;

@Service
public class UserService extends AbstractService<User, UserRepository> {
    public UserService(UserRepository repository) {
        super(repository);
    }
}