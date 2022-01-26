package spd.trello.service;

import org.springframework.stereotype.Service;
import spd.trello.domain.User;
import spd.trello.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserService extends AbstractService<User> {

    public UserService(UserRepository repository) {
        super(repository);
    }

    public User create(String firstName, String lastName, String email) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        return repository.create(user);
    }

    public User update(UUID id, String firstName, String lastName, String email) {
        User byId = repository.findById(id);
        byId.setFirstName(firstName);
        byId.setLastName(lastName);
        byId.setEmail(email);
        byId.setUpdatedDate(LocalDateTime.now());
        return byId;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findByID(UUID id) {
        return repository.findById(id);
    }

    public boolean deleteByID(UUID id) {
        return repository.deleteByID(id);
    }
}
