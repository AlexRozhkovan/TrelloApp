package spd.trello.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import spd.trello.domain.parent_classes.Domain;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = false)
@Data
@ToString(callSuper = true)
public class Reminder extends Domain {

    private LocalDateTime remindOn;
    private Boolean active = Boolean.FALSE;

}
