package spd.trello.domain;

import lombok.Data;
import spd.trello.domain.enumerations.Role;
import spd.trello.domain.parent_classes.Domain;

@Data
public class Member extends Domain {

    private User user;
    private Role role;
}
