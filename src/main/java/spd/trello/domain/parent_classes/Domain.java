package spd.trello.domain.parent_classes;

import lombok.Data;

import java.util.UUID;

@Data
public abstract class Domain {

    protected UUID id = UUID.randomUUID();
}
