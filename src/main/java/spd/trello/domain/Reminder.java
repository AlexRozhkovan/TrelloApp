package spd.trello.domain;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import spd.trello.domain.parent_classes.Domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Generated
@Entity
@Table(name = "reminders")
public class Reminder extends Domain {

    private LocalDateTime start;
    private LocalDateTime finish;
    private LocalDateTime remindOn;
    private Boolean active = Boolean.FALSE;
}
