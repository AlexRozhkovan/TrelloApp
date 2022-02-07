package spd.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import spd.trello.domain.enumerations.Role;
import spd.trello.domain.parent_classes.Resource;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Generated
@Entity
@Table(name = "members")
public class Member extends Resource {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnoreProperties("member")
    private User user;

    @Enumerated(EnumType.STRING)
    private Role role = Role.GUEST;

}
