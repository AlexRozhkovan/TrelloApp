package spd.trello.service;

import spd.trello.domain.Workspace;
import spd.trello.repository_jpa.WorkspaceRepository;
import org.springframework.stereotype.Service;

@Service
public class WorkspaceService extends AbstractService<Workspace, WorkspaceRepository> {
    public WorkspaceService(WorkspaceRepository repository) {
        super(repository);
    }
}