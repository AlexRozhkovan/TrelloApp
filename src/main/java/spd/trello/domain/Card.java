package spd.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import spd.trello.domain.parent_classes.Resource;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Generated
@Entity
@Table(name = "cards")
public class Card extends Resource {

    private String name;
    private String description;
    private Boolean archived = Boolean.FALSE;
    private UUID cardListId;


    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(
            name = "cards_members",
            joinColumns = @JoinColumn(name = "card_id")
    )
    @Column(name = "member_id")
    private Set<UUID> memberIds = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CheckList> checkLists = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Reminder reminder;
}
