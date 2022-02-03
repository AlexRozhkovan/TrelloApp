package spd.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import spd.trello.domain.parent_classes.Domain;
import spd.trello.domain.parent_classes.Resource;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Generated
@Entity
@Table(name = "reminders")
public class Reminder extends Resource {

    private LocalDateTime start;
    private LocalDateTime finish;
    private LocalDateTime remindOn;
    private Boolean active = Boolean.FALSE;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    @JsonIgnoreProperties("reminder")
    private Card card;
}
