package spd.trello.domain;

import spd.trello.domain.parent_classes.Domain;
import lombok.Data;

@Data
public class AttachmentResponse extends Domain {

    private String name;
    private String link;
    private String context;
}