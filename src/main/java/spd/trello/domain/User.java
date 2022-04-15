package spd.trello.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import spd.trello.domain.parent_classes.Resource;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users")
public class User extends Resource {
    @NotBlank(message = "Firstname shouldn`t be blank")
    private String firstName;
    @NotBlank(message = "Lastname shouldn`t be blank")
    private String lastName;
    @Pattern(regexp = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}", message = "Must be a valid email address")
    private String email;
}
