package spd.trello.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spd.trello.domain.Workspace;
import spd.trello.service.WorkspaceService;

@RestController
@RequestMapping(value = "/workspaces", produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkspaceController extends AbstractController<Workspace, WorkspaceService> {

    public WorkspaceController(WorkspaceService service) {
        super(service);
    }
}
