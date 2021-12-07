package trelloApp.domain;

import lombok.RequiredArgsConstructor;
import trelloApp.enumerations.WorkspaceVisibility;
import lombok.Getter;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class Workspace {
    private String name;
    private String description;
    private List<Board> boards;
    private List<Member> members;
    private WorkspaceVisibility visibility;
}