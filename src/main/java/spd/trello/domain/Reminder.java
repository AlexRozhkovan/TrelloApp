package spd.trello.domain;

import lombok.Data;
import spd.trello.domain.parent_classes.Resource;

import java.time.LocalDateTime;

@Data
public class Reminder extends Resource {

    private LocalDateTime remindOn;
    private Boolean active;

}
