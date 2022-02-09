package spd.trello.domain;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import spd.trello.domain.enumerations.BoardVisibility;
import spd.trello.domain.parent_classes.Resource;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Generated
@Entity
@Table(name = "boards")
public class Board extends Resource {

    private String name;
    private String description;
    private Boolean archived = Boolean.FALSE;
    private UUID workspaceId;

    @Enumerated(EnumType.STRING)
    private BoardVisibility visibility = BoardVisibility.PRIVATE;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(
            name = "boards_members",
            joinColumns = @JoinColumn(name = "board_id")
    )
    @Column(name = "member_id")
    private Set<UUID> memberIds = new HashSet<>();

}