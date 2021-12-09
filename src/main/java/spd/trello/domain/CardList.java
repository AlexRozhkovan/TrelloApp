package spd.trello.domain;

import lombok.Data;
import spd.trello.domain.parent_classes.Resource;

import java.util.List;

@Data
public class CardList extends Resource {

    private String name;
    private Boolean archived;
    private List<Card> cards;
}
