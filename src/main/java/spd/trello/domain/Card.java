package spd.trello.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.ToString;
import spd.trello.domain.parent_classes.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = false)
@Data
@ToString(callSuper = true)
@Generated
public class Card extends Resource {

    private String name;
    private String description;
    private Boolean archived = Boolean.FALSE;
    private Reminder reminder;
    private List<Member> assignedMembers = new ArrayList<>();
    private UUID cardListId;
    /*private List<Label> labels = new ArrayList<>();
    private List<Attachment> attachments = new ArrayList<>();
    private List<Comment> comments = new ArrayList<>();
    private List<CheckList> checkLists = new ArrayList<>();*/

}
