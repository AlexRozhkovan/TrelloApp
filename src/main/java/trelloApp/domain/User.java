package trelloApp.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.TimeZone;

@Getter
@Setter
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private TimeZone timeZone;
}
