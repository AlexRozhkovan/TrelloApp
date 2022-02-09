
package spd.trello.domain;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import spd.trello.domain.enumerations.Color;
import spd.trello.domain.parent_classes.Resource;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Getter
@Setter
@Generated
@Entity
@Table(name = "labels")
public class Label extends Resource {

    private String name;

    @Enumerated(EnumType.STRING)
    private Color color = Color.BLACK;
}
