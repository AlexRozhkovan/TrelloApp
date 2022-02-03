package spd.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import spd.trello.domain.parent_classes.Resource;

import javax.persistence.*;

@Getter
@Setter
@Generated
@Entity
@Table(name = "check_lists")
public class CheckList extends Resource {

    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "card_id", referencedColumnName = "id")
    @JsonIgnoreProperties("checkList")
    private Card card;
}
