package spd.trello.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import spd.trello.domain.parent_classes.Resource;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@ToString(callSuper = true)
public class Comment extends Resource {

    private String text;
    private List<Attachment> attachments  = new ArrayList<>();
    private Member member;

}
