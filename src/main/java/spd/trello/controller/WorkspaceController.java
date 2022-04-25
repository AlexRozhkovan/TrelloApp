package spd.trello.controller;

import spd.trello.domain.Workspace;
import spd.trello.service.WorkspaceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workspaces")
public class WorkspaceController extends AbstractController<Workspace, WorkspaceService> {
    public WorkspaceController(WorkspaceService service) {
        super(service);
    }
}