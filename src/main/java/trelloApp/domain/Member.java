package trelloApp.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import trelloApp.enumerations.RoleEnum;

@Getter
@Setter
@RequiredArgsConstructor
public class Member {
    private User user;
    private RoleEnum role;
}
