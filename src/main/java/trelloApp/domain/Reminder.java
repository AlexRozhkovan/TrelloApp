package trelloApp.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Reminder {
    private LocalDateTime start;
    private LocalDateTime end;
    private LocalDateTime remindOn;
    private boolean isActive;

}
