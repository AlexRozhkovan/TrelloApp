package spd.trello.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import spd.trello.domain.enumerations.Role;
import spd.trello.domain.parent_classes.Resource;

@EqualsAndHashCode(callSuper = false)
@Data
@ToString(callSuper = true)
public class Member extends Resource {

    private User user;
    private Role role;
}
