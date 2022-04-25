package spd.trello.domain;

import spd.trello.domain.parent_classes.Resource;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "labels")
@Entity
public class Label extends Resource {

    @Column(name = "color")
    private String color;
}