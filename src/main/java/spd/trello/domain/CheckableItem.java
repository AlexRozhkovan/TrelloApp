package spd.trello.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.ToString;
import spd.trello.domain.parent_classes.Domain;

import java.util.UUID;

@EqualsAndHashCode(callSuper = false)
@Data
@ToString(callSuper = true)
@Generated
public class CheckableItem extends Domain {

    private String name;
    private Boolean checked = Boolean.FALSE;
    private UUID checkListId;
}
