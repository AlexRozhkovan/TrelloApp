package spd.trello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spd.trello.domain.Workspace;
import spd.trello.exception.ResourceNotFoundException;
import spd.trello.repository.WorkspaceRepository;
import spd.trello.service.WorkspaceService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/workspaces", produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkspaceController extends AbstractController<Workspace, WorkspaceService> {

    public WorkspaceController(WorkspaceService service) {
        super(service);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Workspace> readByName(@PathVariable String name) {
        List<Workspace> result = service.findByName(name);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
