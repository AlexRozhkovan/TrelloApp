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
    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "checklist_id", referencedColumnName = "id")
    private CheckList checkList;

    @Column(name = "name")
    private String name;

    private Boolean checked = false;
}
