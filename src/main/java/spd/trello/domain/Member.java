package spd.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import spd.trello.domain.enumerations.Role;
import spd.trello.domain.parent_classes.Resource;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Generated
@Entity
@Table(name = "members")public class Member extends Resource {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnoreProperties("member")
    private User user;

    @Enumerated(EnumType.STRING)
    private Role role = Role.GUEST;

    @ManyToMany(mappedBy = "members")
    private Set<Workspace> workspaces;

    @ManyToMany(mappedBy = "members")
    private Set<Card> cards;

    @ManyToMany(mappedBy = "members")
    private Set<Board> boards;

    /*@ElementCollection
    @CollectionTable(
            name = "boards_members",
            joinColumns=@JoinColumn(name= "member_id")
    )
    @Column(name = "board_id")
    private Set<UUID> boardsIds = new HashSet<>();*/

    /*@ElementCollection
    @CollectionTable(
            name = "cards_members",
            joinColumns=@JoinColumn(name= "member_id")
    )
    @Column(name = "card_id")
    private Set<UUID> cardsIds = new HashSet<>();*/

}
