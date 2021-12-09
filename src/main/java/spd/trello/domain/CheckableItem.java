package spd.trello.domain;

import lombok.Data;
import spd.trello.domain.parent_classes.Domain;

@Data
public class CheckableItem extends Domain {

    private String name;
    private Boolean checked;
}
