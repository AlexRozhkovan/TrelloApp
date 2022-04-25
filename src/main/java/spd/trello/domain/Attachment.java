package spd.trello.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import spd.trello.domain.parent_classes.Domain;

import javax.persistence.*;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "attachments")
@Entity
public class Attachment extends Domain {

    @Column(name = "name")
    private String name;

    @Column(name = "context")
    private String context;

    @Lob
    private byte[] file;

    @Column(name = "card_id")
    private UUID cardId;
}