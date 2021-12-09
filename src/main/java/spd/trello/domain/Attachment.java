package spd.trello.domain;

import lombok.Data;
import spd.trello.domain.parent_classes.Resource;

import java.io.File;

@Data
public class Attachment extends Resource {

    private String name;
    private File file;
    private String link;
}
