package spd.trello.domain;

import lombok.*;
import spd.trello.domain.parent_classes.Domain;

import java.io.File;
import java.util.UUID;

@EqualsAndHashCode(callSuper = false)
@Data
@ToString(callSuper = true)
public class Attachment extends Domain {

    private String name;
    private String type;
    private String link;
    private UUID cardId;
    private UUID commentId;

}
