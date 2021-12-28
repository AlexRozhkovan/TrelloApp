package spd.trello.domain.parent_classes;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import spd.trello.domain.Member;
import spd.trello.domain.User;

import java.time.LocalDateTime;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class Resource extends Domain {

    private Member createdBy; //email ??
    private Member updatedBy; //email ??
    private LocalDateTime createdDate = LocalDateTime.now();
    LocalDateTime updatedDate = LocalDateTime.now();

}
