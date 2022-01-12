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
    private Boolean archived = Boolean.FALSE;
    private BoardVisibility visibility = BoardVisibility.PRIVATE;
    private List<Member> members = new ArrayList<>();
    private List<CardList> cardLists= new ArrayList<>();

}
