package spd.trello.domain.parent_classes;

import lombok.Data;
import lombok.Generated;

import java.util.UUID;

@Data
@Generated
public abstract class Domain {

    protected UUID id = UUID.randomUUID();
}
