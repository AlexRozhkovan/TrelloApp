package spd.trello.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.UniqueElements;
import spd.trello.domain.enumerations.BoardVisibility;
import spd.trello.domain.parent_classes.MainArchived;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "boards")
public class Board extends MainArchived {

    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    @Column(name = "workspace_id")
    private UUID workspaceId;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(
            name = "board_member",
            joinColumns = @JoinColumn(name = "board_id"))
    @Column(name = "member_id")
    @UniqueElements(message = "member already exist")
    @NotEmpty(message = "Add minimum 1 member")
    private List<UUID> members = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(name = "visibility")
    private BoardVisibility visibility = BoardVisibility.PUBLIC;

}
