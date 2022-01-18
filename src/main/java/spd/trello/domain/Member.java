package spd.trello.domain;

import lombok.*;
import spd.trello.domain.enumerations.Role;
import spd.trello.domain.parent_classes.Resource;

import java.util.UUID;

@EqualsAndHashCode(callSuper = false)
@Data
@ToString(callSuper = true)
public class Member extends Resource {

    private UUID user;
    private Role role = Role.GUEST;

}
