package spd.trello.domain;

import lombok.Data;
import spd.trello.domain.parent_classes.Resource;

import java.util.List;

@Data
public class Card extends Resource {

    private String name;
    private String description;
    private Boolean archived;
    private List<Member> assignedMembers;
    private List<Label> labels;
    private List<Attachment> attachments;
    private List<Comment> comments;
    private List<CheckList> checkLists;
    private Reminder reminder;

}
