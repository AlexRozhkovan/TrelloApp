package trelloApp.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class Card {
    private String name;
    private String description;
    private boolean isArchived;
    private List<Member> assignedMembers;
    private List<Label> labels;
    private List<Attachment> attachments;
    private List<Comment> comments;
    private List<CheckList> checkLists;
    private LocalDateTime creationDate;
    private Reminder reminder;

}
