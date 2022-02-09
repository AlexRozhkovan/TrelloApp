package spd.trello.domain;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import spd.trello.domain.parent_classes.Resource;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Generated
@Entity
@Table(name = "comments")
public class Comment extends Resource {

    private String text;
    private UUID userId;
    private UUID cardId;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @CollectionTable(
            name = "comments"
    )
    @Column(name = "attachment_id")
    private Set<UUID> attachmentIds = new HashSet<>();
}
