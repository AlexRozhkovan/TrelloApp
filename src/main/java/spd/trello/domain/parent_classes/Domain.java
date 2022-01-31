package spd.trello.domain.parent_classes;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Getter
@Setter
@Generated
@MappedSuperclass
public abstract class Domain {

    @Id
    private UUID id = UUID.randomUUID();
}
