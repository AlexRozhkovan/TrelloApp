package spd.trello.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import spd.trello.domain.parent_classes.Domain;

@EqualsAndHashCode(callSuper = false)
@Data
@ToString(callSuper = true)
public class CheckableItem extends Domain {

    private String name;
    private Boolean checked;
}
