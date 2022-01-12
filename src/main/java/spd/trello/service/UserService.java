package spd.trello.service;

import spd.trello.domain.User;
import spd.trello.repository.UserRepository;

import java.util.List;
import java.util.UUID;

public class UserService extends AbstractService<User> {
    public UserService(UserRepository repository) {
        super(repository);
    }

    public User create(String firstName, String lastName, String email) {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        repository.create(user);
        return user;
    }

    public boolean deleteAll() {
        repository.deleteAll();
        return true;
    }

    public User update(UUID id) {
        User byID = repository.findByID(id);
        repository.update(byID);
        return byID;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public void print(User entity) {
        System.out.println(entity);
    }

    public User findByID(UUID id) {
        return repository.findByID(id);
    }

    public boolean deleteByID(UUID id) {
        repository.deleteByID(id);
        return true;
    }
}
