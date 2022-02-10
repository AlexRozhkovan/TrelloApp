package spd.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import spd.trello.domain.parent_classes.Domain;

import javax.persistence.*;

@Getter
@Setter
@Generated
@Entity
@Table(name = "checkable_items")
public class CheckableItem extends Domain {

    private String name;
    private Boolean checked = Boolean.FALSE;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "check_list_id", referencedColumnName = "id")
    private CheckList checkList;

}
