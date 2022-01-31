package spd.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
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


    /*@ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(
            name = "cards_members",
            joinColumns=@JoinColumn(name= "card_id")
    )
    @Column(name = "member_id")
    private Set<UUID> memberIds = new HashSet<>();*/

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cards_members",
            joinColumns = @JoinColumn(name = "card_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id"))
    private Set<Member> members;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cardlist_id", referencedColumnName = "id")
    @JsonIgnoreProperties("card")
    private CardList cardList;



    /*private List<Label> labels = new ArrayList<>();
    private Reminder reminder;
    private List<Attachment> attachments = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();
    private List<CheckList> checkLists = new ArrayList<>();*/

}
