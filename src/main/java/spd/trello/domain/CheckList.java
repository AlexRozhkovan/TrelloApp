package spd.trello.domain;

import lombok.*;
import spd.trello.domain.parent_classes.Domain;
import spd.trello.domain.parent_classes.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = false)
@Data
@ToString(callSuper = true)
@Generated
public class CheckList extends Domain {

    private String name;
    //private List<CheckableItem> items = new ArrayList<>();

}