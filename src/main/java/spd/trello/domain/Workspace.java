package spd.trello.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import spd.trello.domain.enumerations.WorkspaceVisibility;
import spd.trello.domain.parent_classes.Resource;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@ToString(callSuper = true)
public class Workspace extends Resource {

    private String name;
    private String description;
    private List<Board> boards;
    private List<Member> members;
    private WorkspaceVisibility visibility = WorkspaceVisibility.PRIVATE;
}