package spd.trello.domain;

import lombok.Data;
import spd.trello.domain.enumerations.WorkspaceVisibility;
import spd.trello.domain.parent_classes.Resource;

import java.util.List;

@Data
public class Workspace extends Resource {

    private String name;
    private String description;
    private List<Board> boards;
    private List<Member> members;
    private WorkspaceVisibility visibility;
}