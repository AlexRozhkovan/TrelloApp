package spd.trello.domain;

import lombok.Data;
import spd.trello.domain.parent_classes.Domain;
import spd.trello.enumerations.Role;

@Data
public class Member extends Domain {

    private User user;
    private Role role;
}
