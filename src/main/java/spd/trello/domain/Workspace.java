package spd.trello.domain;

import lombok.*;
import spd.trello.domain.enumerations.WorkspaceVisibility;
import spd.trello.domain.parent_classes.Resource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = false)
@Data
@ToString(callSuper = true)
@Generated
public class Workspace extends Resource {

    private String name;
    private String description;
    private WorkspaceVisibility visibility = WorkspaceVisibility.PUBLIC;
    private List<Member> members = new ArrayList<>();

}