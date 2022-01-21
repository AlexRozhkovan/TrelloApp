package spd.trello.service;

import spd.trello.domain.User;
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
        workspace.setCreatedBy(createdBy);
        workspace.setName(name);
        workspace.setDescription(description);
        workspace.setVisibility(visibility);
        repository.create(workspace);
        return workspace;
    }

    public Workspace update(UUID id, String updatedBy, String name, String description, WorkspaceVisibility visibility) {
        Workspace byID = repository.findById(id);
        byID.setUpdatedBy(updatedBy);
        byID.setName(name);
        byID.setDescription(description);
        byID.setVisibility(visibility);
        byID.setUpdatedDate(LocalDateTime.now());
        return repository.update(byID);
    }

    public List<Workspace> findAll() {
        return repository.findAll();
    }

    public Workspace findByID(UUID id) {
        return repository.findById(id);
    }

    public boolean deleteAll() {
        return repository.deleteAll();
    }

    public boolean deleteByID(UUID id) {
        return repository.deleteByID(id);
    }
}
