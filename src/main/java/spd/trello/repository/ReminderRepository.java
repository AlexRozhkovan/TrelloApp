package spd.trello.repository;

import org.springframework.stereotype.Repository;
import spd.trello.domain.Comment;
import spd.trello.domain.Reminder;

@Repository
public interface ReminderRepository extends IRepository<Reminder>{
}
