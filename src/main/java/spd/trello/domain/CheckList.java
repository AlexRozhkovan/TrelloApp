package spd.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import spd.trello.domain.parent_classes.Resource;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "checklists")
public class CheckList extends Resource {

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            targetEntity = CheckableItem.class,
            mappedBy = "checkList")
    @JsonManagedReference
    @JsonIgnoreProperties(value = "checkList", allowSetters = true)
    private List<CheckableItem> items = new ArrayList<>();
}
