package spd.trello.service;

import spd.trello.domain.Workspace;
import spd.trello.domain.enumerations.WorkspaceVisibility;
import spd.trello.repository.WorkspaceRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class WorkspaceService extends AbstractService<Workspace> {
    public WorkspaceService(WorkspaceRepository repository) {
        super(repository);
    }

    public Workspace create(String createdBy, String name, String description, WorkspaceVisibility visibility) {
        Workspace workspace = new Workspace();
        workspace.setId(UUID.randomUUID());
        workspace.setCreatedBy(createdBy);
        workspace.setName(name);
        workspace.setDescription(description);
        workspace.setVisibility(visibility);
        repository.create(workspace);
        return workspace;
    }

    public boolean deleteAll() {
        repository.deleteAll();
        return true;
    }

    public Workspace update(UUID id) {
        Workspace byID = repository.findByID(id);
        repository.update(byID);
        return byID;
    }

    public List<Workspace> findAll() {
        return repository.findAll();
    }

    public void print(Workspace entity) {
        System.out.println(entity);
    }

    public Workspace findByID(UUID id) {
        return repository.findByID(id);
    }

    public boolean deleteByID(UUID id) {
        repository.deleteByID(id);
        return true;
    }
}
