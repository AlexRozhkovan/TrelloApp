package spd.trello.domain;

import lombok.Data;
import spd.trello.domain.enumerations.BoardVisibility;
import spd.trello.domain.parent_classes.Resource;

import java.util.List;

@Data
public class Board extends Resource {

    private String name;
    private String description;
    // private boolean favouriteStatus; TODO where is need to be?
    private Boolean archived;
    private BoardVisibility visibility;
    private List<Member> members;
    private List<CardList> cardLists;
}
