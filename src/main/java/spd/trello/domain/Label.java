package spd.trello.domain;

import lombok.Data;
import spd.trello.domain.enumerations.Color;
import spd.trello.domain.parent_classes.Domain;

@Data
public class Label extends Domain {

    private String name;
    private Color color;
}
