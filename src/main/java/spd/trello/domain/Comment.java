package spd.trello.domain;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import spd.trello.domain.parent_classes.Resource;

import javax.persistence.Entity;
import javax.persistence.Table;
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
}