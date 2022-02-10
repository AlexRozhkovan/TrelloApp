package spd.trello.service;

import org.springframework.stereotype.Service;
import spd.trello.domain.Workspace;
import spd.trello.repository.WorkspaceRepository;

import java.util.List;
import java.util.UUID;

@Service
public class WorkspaceService extends AbstractService<Workspace, WorkspaceRepository> {

    public WorkspaceService(WorkspaceRepository repository) {
        super(repository);
    }

    public Workspace create(Workspace entity) {
        return super.save(entity);
    }

    public Workspace update(Workspace entity) {
        return super.update(entity);
    }

    public List<Workspace> findAll() {
        return super.getAll();
    }

    public Workspace findByID(UUID id) {
        return super.getById(id);
    }

    public void deleteByID(UUID id) {
        super.deleteById(id);
    }
}