package spd.trello.service;

import spd.trello.domain.Workspace;
import spd.trello.domain.enumerations.WorkspaceVisibility;
import spd.trello.domain.parent_classes.Resource;
import spd.trello.exception.ResourceNotFoundException;
import spd.trello.repository.IRepository;

import java.util.List;
import java.util.UUID;

public abstract class AbstractService<E extends Resource, R extends IRepository<E>>
        implements CommonService<E>{
    R repository;

    public AbstractService(R repository){
        this.repository = repository;
    }

    @Override
    public E save(E entity) {
        return repository.save(entity);
    }

    @Override
    public E update(E entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(UUID id) {
       repository.deleteById(id);
    }

    @Override
    public E getById(UUID id) {
        return repository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<E> getAll() {
        return repository.findAll();
    }
}
