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


    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(
            name = "cards_members",
            joinColumns=@JoinColumn(name= "card_id")
    )
    @Column(name = "member_id")
    private Set<UUID> memberIds = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "card_list_id", referencedColumnName = "id")
    @JsonIgnoreProperties("card")
    private CardList cardList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "card", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH})
    @JsonIgnoreProperties("card")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "card", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("card")
    private List<CheckList> checkLists = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "card", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("card")
    private List<Reminder> reminders = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "card", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("card")
    private List<Attachment> attachments = new ArrayList<>();
}
