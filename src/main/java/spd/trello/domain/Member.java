package spd.trello.domain;

import spd.trello.domain.enums.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import spd.trello.domain.parent_classes.Resource;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "members")
public class Member extends Resource {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private Role role = Role.MEMBER;
}