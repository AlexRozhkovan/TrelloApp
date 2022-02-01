package spd.trello.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import spd.trello.domain.parent_classes.Resource;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Generated
@Entity
@Table(name = "cardlists")
public class CardList extends Resource {

    private String name;
    private Boolean archived = Boolean.FALSE;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "board_id", referencedColumnName = "id")
    @JsonIgnoreProperties("cardList")
    private Board board;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cardList", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("cardList")
    private List<Card> cards = new ArrayList<>();

}
