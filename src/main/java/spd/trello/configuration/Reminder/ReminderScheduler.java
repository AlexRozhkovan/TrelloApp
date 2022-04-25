package spd.trello.configuration.Reminder;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import spd.trello.domain.Reminder;
import spd.trello.repository.ReminderRepository;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@EnableScheduling
public class ReminderScheduler {

    private final ReminderRepository repository;

    public ReminderScheduler(ReminderRepository repository) {
        this.repository = repository;
    }

    @Scheduled(fixedRate = 60000)
    public void RemindMessage() {
        List<Reminder> reminders = repository.findAllByRemindOnBetween(LocalDateTime.now().minusMinutes(1), LocalDateTime.now());
        for (Reminder reminder : reminders) {
            System.out.println("Reminder: " + reminder.getId() + "has been activated");
        }
    }
}
