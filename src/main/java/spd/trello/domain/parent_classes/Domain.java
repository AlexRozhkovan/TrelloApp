package spd.trello.domain.parent_classes;

import lombok.Data;

import java.util.UUID;

@Data
public abstract class Domain {

    private UUID id;
    public Domain() {
        this.id = UUID.randomUUID();
    }
}
