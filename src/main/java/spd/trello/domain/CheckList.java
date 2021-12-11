package spd.trello.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import spd.trello.domain.parent_classes.Resource;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@ToString(callSuper = true)
public class CheckList extends Resource {

    private String name;
    private List<CheckableItem> items = new ArrayList<>();
}
