package spd.trello.domain;

import lombok.*;
import spd.trello.domain.parent_classes.Domain;
import spd.trello.domain.parent_classes.Resource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = false)
@Data
@ToString(callSuper = true)
public class Comment extends Domain {

    private String text;
    private List<Attachment> attachments;
    private Member member;
    private LocalDateTime dateTime;

}
