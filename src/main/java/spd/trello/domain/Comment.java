package spd.trello.domain;

import lombok.Data;
import spd.trello.domain.parent_classes.Resource;

import java.util.List;

@Data
public class Comment extends Resource {

    private String text;
    private List<Attachment> attachments;
    private Member member;

}
