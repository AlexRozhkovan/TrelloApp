package spd.trello.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spd.trello.domain.Workspace;
import spd.trello.service.WorkspaceService;

import java.util.List;

@RestController
@RequestMapping(value = "/workspaces", produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkspaceController extends AbstractController<Workspace, WorkspaceService> {

    public WorkspaceController(WorkspaceService service) {
        super(service);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Workspace> readByName(@PathVariable String name) {
        List<Workspace> result = service.findByName(name);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
