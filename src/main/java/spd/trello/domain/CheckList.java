package spd.trello.domain;

import lombok.Data;
import spd.trello.domain.parent_classes.Resource;

import java.util.List;

@Data
public class CheckList extends Resource {

    private String name;
    private List<CheckableItem> items;
}
