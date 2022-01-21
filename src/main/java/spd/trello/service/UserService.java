package spd.trello.service;

import spd.trello.domain.Member;
import spd.trello.domain.User;
import spd.trello.domain.enumerations.Role;
import spd.trello.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class UserService extends AbstractService<User> {
    public UserService(UserRepository repository) {
        super(repository);
    }

    public User create(String firstName, String lastName, String email) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        repository.create(user);
        return user;
    }

    public User update(UUID id, String firstName, String lastName, String email) {
        User byID = repository.findById(id);
        byID.setFirstName(firstName);
        byID.setLastName(lastName);
        byID.setEmail(email);
        byID.setUpdatedDate(LocalDateTime.now());
        return repository.update(byID);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findByID(UUID id) {
        return repository.findById(id);
    }

    public boolean deleteAll() {
        return repository.deleteAll();
    }

    public boolean deleteByID(UUID id) {
        return repository.deleteByID(id);
    }
}
