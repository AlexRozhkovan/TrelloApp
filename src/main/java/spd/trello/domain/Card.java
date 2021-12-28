package spd.trello.domain;

import lombok.*;
import spd.trello.domain.parent_classes.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = false)
@Data
@ToString(callSuper = true)
public class Card extends Resource {

    private String name;
    private String description;
    private Boolean archived = Boolean.FALSE;
    private List<Member> assignedMembers;
    private List<Label> labels;
    private List<Attachment> attachments;
    private List<Comment> comments ;
    private List<CheckList> checkLists;
    private Reminder reminder;

}
