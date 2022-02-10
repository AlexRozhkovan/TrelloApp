package spd.trello.domain;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import spd.trello.domain.parent_classes.Resource;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Generated
@Entity
@Table(name = "users")
public class User extends Resource {
    private String firstName;
    private String lastName;
    private String email;
}