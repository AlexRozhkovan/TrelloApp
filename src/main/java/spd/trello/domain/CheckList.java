package spd.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import spd.trello.domain.parent_classes.Domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Generated
@Entity
@Table(name = "check_lists")
public class CheckList extends Domain {

    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    private Card card;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "checkList", cascade = CascadeType.ALL)
    private List<CheckableItem> items = new ArrayList<>();

}
