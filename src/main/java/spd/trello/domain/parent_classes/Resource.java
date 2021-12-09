package spd.trello.domain.parent_classes;

import lombok.Data;
import spd.trello.domain.Member;

import java.time.LocalDateTime;

@Data
public class Resource extends Domain {

    private Member createdBy;
    private Member updatedBy;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
