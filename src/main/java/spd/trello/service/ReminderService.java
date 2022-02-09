package spd.trello.service;

import org.springframework.stereotype.Service;
import spd.trello.domain.Reminder;
import spd.trello.exception.IsAlreadyExist;
import spd.trello.repository.ReminderRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ReminderService extends AbstractService<Reminder, ReminderRepository> {

    public ReminderService(ReminderRepository repository) {
        super(repository);
    }

    public Reminder create(Reminder entity) {
        if (isExists(entity)) {
            throw new IsAlreadyExist();
        } else {
            return super.save(entity);
        }
    }

    public Reminder update(Reminder entity) {
        return super.update(entity);
    }

    public List<Reminder> findAll() {
        return super.getAll();
    }

    public Reminder findByID(UUID id) {
        return super.getById(id);
    }

    public void deleteByID(UUID id) {
        super.deleteById(id);
    }
}