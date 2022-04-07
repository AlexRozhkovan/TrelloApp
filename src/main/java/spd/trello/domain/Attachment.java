package spd.trello.domain;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import spd.trello.domain.parent_classes.Domain;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Generated
@Entity
@Table(name = "attachments")
public class Attachment extends Domain {

    private String context;
    private String name;
    @Lob
    private byte[] file;
    private UUID cardId;
}