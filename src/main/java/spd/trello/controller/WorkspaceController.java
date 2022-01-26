package spd.trello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import spd.trello.domain.Workspace;
import spd.trello.repository.WorkspaceRepository;
import spd.trello.service.WorkspaceService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkspaceController {
    @Autowired
    WorkspaceRepository workspaceRepository;
    @Autowired
    WorkspaceService workspaceService;

    @GetMapping("/workspaces")
    @ResponseStatus(HttpStatus.OK)
    public List<Workspace> getAll(){
        return workspaceService.findAll();
    }

    @GetMapping("/workspaces/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Workspace getById(@PathVariable UUID id){
        return workspaceService.findByID(id);
    }

    @PostMapping("/workspaces")
    @ResponseStatus(HttpStatus.CREATED)
    public Workspace saveWorkspace(@RequestBody Workspace workspace) {
        return workspaceRepository.create(workspace);
    }

}
