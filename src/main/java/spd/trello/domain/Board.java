package spd.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import spd.trello.domain.enumerations.BoardVisibility;
import spd.trello.domain.parent_classes.Resource;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Generated
@Entity
@Table(name = "boards")
public class Board extends Resource {

    private String name;
    private String description;
    private Boolean archived = Boolean.FALSE;

    @Enumerated(EnumType.STRING)
    private BoardVisibility visibility = BoardVisibility.PRIVATE;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "workspace_id", referencedColumnName = "id")
    @JsonIgnoreProperties("board")
    private Workspace workspace;

    /*@ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(
            name = "boards_members",
            joinColumns=@JoinColumn(name= "board_id")
    )
    @Column(name = "member_id")
    private Set<UUID> memberIds = new HashSet<>();*/

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "boards_members",
            joinColumns = @JoinColumn(name = "board_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id"))
    private Set<Member> members;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("board")
    private List<CardList> cardLists = new ArrayList<>();

}
