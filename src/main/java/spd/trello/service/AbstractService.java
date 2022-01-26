package spd.trello.service;

import spd.trello.domain.Workspace;
import spd.trello.domain.enumerations.WorkspaceVisibility;
import spd.trello.repository.IRepository;

public abstract class AbstractService<T> {

    protected IRepository<T> repository;

    public AbstractService(IRepository<T> repository) {
        this.repository = repository;
    }

}
