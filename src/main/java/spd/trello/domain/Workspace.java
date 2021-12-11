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
    private List<Board> boards = new ArrayList<>();
    private List<Member> members = new ArrayList<>();
    private WorkspaceVisibility visibility = WorkspaceVisibility.PUBLIC;
}