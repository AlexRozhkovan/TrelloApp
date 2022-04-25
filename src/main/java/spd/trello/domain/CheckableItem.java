package spd.trello.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import spd.trello.domain.parent_classes.Domain;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "items")
public class CheckableItem extends Domain {

    @Column(name = "name")
    private String name;

    @Column(name = "checked")
    private Boolean checked = false;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "checklist_id", referencedColumnName = "id")
    private CheckList checkList;
}