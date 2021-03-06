package spd.trello.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.UniqueElements;
import spd.trello.domain.enums.BoardVisibility;
import spd.trello.domain.parent_classes.ArchivedResource;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "boards")
public class Board extends ArchivedResource {

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
    @UniqueElements(message = "Member is exist")
    @NotEmpty(message = "You can't create board without members")
    private List<UUID> members = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private BoardVisibility visibility = BoardVisibility.PUBLIC;
}