package spd.trello.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import spd.trello.domain.enumerations.Color;
import spd.trello.domain.parent_classes.Resource;

@EqualsAndHashCode(callSuper = false)
@Data
@ToString(callSuper = true)
public class Label extends Resource {

    private String name;
    private Color color = Color.NULL;
}
