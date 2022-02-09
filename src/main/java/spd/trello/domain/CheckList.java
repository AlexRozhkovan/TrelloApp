package spd.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import spd.trello.domain.parent_classes.Domain;

import javax.persistence.*;

@Getter
@Setter
@Generated
@Entity
@Table(name = "check_lists")
public class CheckList extends Domain {

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("checkList")
    private CheckableItem checkableItem;

    private String name;
}
