package spd.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import spd.trello.domain.parent_classes.Domain;
import spd.trello.domain.parent_classes.Resource;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Getter
@Setter
@Generated
@Entity
@Table(name = "users")
public class User extends Resource{
    private String firstName;
    private String lastName;
    private String email;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private List<Member> members = new ArrayList<>();
}
