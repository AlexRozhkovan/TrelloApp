package spd.trello.domain.parent_classes;

import lombok.*;

import java.util.UUID;

@Data
@Generated
public abstract class Domain {

    protected UUID id = UUID.randomUUID();
}
