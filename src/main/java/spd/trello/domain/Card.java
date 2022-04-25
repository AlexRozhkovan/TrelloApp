package spd.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.UniqueElements;
import spd.trello.domain.parent_classes.ArchivedResource;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "cards")
public class Card extends ArchivedResource {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "cardlist_id")
    private UUID cardListId;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(
            name = "card_member",
            joinColumns = @JoinColumn(name = "card_id"))
    @Column(name = "member_id")
    @UniqueElements(message = "Member is exist")
    @NotEmpty(message = "You can't create card without members")
    private List<UUID> members = new ArrayList<>();

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "label_card",
            joinColumns = @JoinColumn(name = "card_id"))
    @Column(name = "label_id")
    @UniqueElements(message = "Label is exist")
    private List<UUID> labels;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "reminder_id", referencedColumnName = "id")
    @JsonIgnoreProperties("card")
    private Reminder reminder;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "checklist_id", referencedColumnName = "id")
    @JsonIgnoreProperties("card")
    private CheckList checkList;
}