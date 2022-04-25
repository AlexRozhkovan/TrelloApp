package spd.trello.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import spd.trello.domain.parent_classes.Resource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
public class User extends Resource {

    @Column(name = "first_name")
    @NotBlank(message = "Firstname can't be blank")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Lastname can't not be blank")
    private String lastName;

    @Column(name = "email")
    @Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}", message = "Invalid email address")
    private String email;
}