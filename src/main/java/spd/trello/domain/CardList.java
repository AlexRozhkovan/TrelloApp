package spd.trello.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import spd.trello.domain.parent_classes.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = false)
@Data
@ToString(callSuper = true)
public class CardList extends Resource {

    private String name;
    private Boolean archived = Boolean.FALSE;
    private UUID boardId;
}
