package trelloApp.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.TimeZone;

@Getter
@Setter
@RequiredArgsConstructor
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private TimeZone timeZone;
}
