package spd.trello.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import spd.trello.domain.parent_classes.Domain;

import java.io.File;

@EqualsAndHashCode(callSuper = false)
@Data
@ToString(callSuper = true)
public class Attachment extends Domain {

    private String name;
    private File file;
    private String link;


}
