package spd.trello.domain.parent_classes;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Data
@MappedSuperclass
public class Domain {
    @Id
    protected UUID id = UUID.randomUUID();
}
