package spd.trello.domain.parent_classes;

import lombok.Data;
import lombok.EqualsAndHashCode;
import spd.trello.domain.Member;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class Resource extends Domain {

    Member createdBy;
    Member updatedBy;
    LocalDateTime createdDate = LocalDateTime.now();
    LocalDateTime updatedDate = LocalDateTime.now();
}
