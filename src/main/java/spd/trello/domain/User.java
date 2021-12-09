package spd.trello.domain;

import lombok.Data;
import spd.trello.domain.parent_classes.Domain;

import java.util.TimeZone;

@Data
public class User extends Domain {

    private String firstName;
    private String lastName;
    private String email;
    private TimeZone timeZone;
}
