package spd.trello.domain;

import lombok.Data;
import spd.trello.domain.parent_classes.Domain;
import spd.trello.enumerations.Color;

@Data
public class Label extends Domain {

    private String name;
    private Color color;
}
