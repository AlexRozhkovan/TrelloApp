package spd.trello.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import spd.trello.domain.parent_classes.Resource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
public class User extends Resource {

    @Column(name = "first_name")
    @NotBlank(message = "Firstname shouldn`t be blank")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Lastname shouldn`t be blank")
    private String lastName;

    @Column(name = "email")
    @Email(message = "Must be a valid email address")
    private String email;
}
