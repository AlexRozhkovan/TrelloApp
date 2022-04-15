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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "cards")
public class Card extends MainArchived {
    private String name;
    private String description;

    @Column(name = "cardlist_id")
    private UUID cardListId;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(
            name = "card_member",
            joinColumns = @JoinColumn(name = "card_id"))
    @Column(name = "member_id")
    @NotEmpty(message = "card must contain at least one member")
    private List<UUID> assignedMembers = new ArrayList<>();

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "label_card",
            joinColumns = @JoinColumn(name = "card_id"))
    @Column(name = "label_id")
    @UniqueElements(message = "Label cannot added twice")
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
