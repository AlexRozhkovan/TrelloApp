package spd.trello.domain;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import spd.trello.domain.enumerations.Role;
import spd.trello.domain.parent_classes.Resource;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Generated
@Entity
@Table(name = "members")
public class Member extends Resource {

    private UUID userId;

    @Enumerated(EnumType.STRING)
    private Role role = Role.GUEST;
}