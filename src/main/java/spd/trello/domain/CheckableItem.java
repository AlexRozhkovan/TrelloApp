package spd.trello.domain;

import lombok.*;
import spd.trello.domain.parent_classes.Domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Generated
@Entity
@Table(name = "checkable_items")
public class CheckableItem extends Domain {

    private String name;
    private Boolean checked = Boolean.FALSE;
}
