package spd.trello.service;

import org.springframework.stereotype.Service;
import spd.trello.domain.User;
import spd.trello.domain.Workspace;
import spd.trello.repository.UserRepository;
import spd.trello.repository.WorkspaceRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserService extends AbstractService<User, UserRepository> {

    public UserService(UserRepository repository) {
        super(repository);
    }

    public User create(User entity) {
        entity.getMembers().forEach(member -> member.setUser(entity));
        return super.save(entity);
    }

    public User update(User entity) {
        return super.update(entity);
    }

    public List<User> findAll() {
        return super.getAll();
    }

    public User findByID(UUID id) {
        return super.getById(id);
    }

    public void deleteByID(UUID id) {
        super.deleteById(id);
    }
}
