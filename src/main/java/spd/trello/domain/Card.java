package spd.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.UniqueElements;
import spd.trello.domain.parent_classes.MainArchived;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "cards")
public class Card extends MainArchived {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "card_list_id")
    private UUID cardListId;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(
            name = "card_member",
            joinColumns = @JoinColumn(name = "card_id"))
    @Column(name = "member_id")
    @UniqueElements(message = "Member already exist")
    @NotEmpty(message = "Add minimum 1 member")
    private List<UUID> members = new ArrayList<>();

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "label_card",
            joinColumns = @JoinColumn(name = "card_id"))
    @Column(name = "label_id")
    @UniqueElements(message = "Label already exist")
    private List<UUID> labels;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "reminder_id", referencedColumnName = "id")
    @JsonIgnoreProperties("card")
    private Reminder reminder;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "check_list_id", referencedColumnName = "id")
    @JsonIgnoreProperties("card")
    private CheckList checkList;
}
