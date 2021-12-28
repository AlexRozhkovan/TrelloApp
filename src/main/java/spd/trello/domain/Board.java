package spd.trello.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import spd.trello.domain.enumerations.BoardVisibility;
import spd.trello.domain.parent_classes.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = false)
@Data
@ToString(callSuper = true)
public class Board extends Resource {

    private String name;
    private String description;
    // private boolean favouriteStatus; TODO where is need to be?
    private Boolean archived = Boolean.FALSE;
    private BoardVisibility visibility = BoardVisibility.PRIVATE;
    private List<Member> members;
    private List<CardList> cardLists;

}
