package spd.trello.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import spd.trello.domain.parent_classes.Resource;
import spd.trello.exception.NotFoundException;
import spd.trello.repository.IRepository;

import java.util.List;
import java.util.UUID;

public abstract class AbstractService<E extends Resource, R extends IRepository<E>>
        implements CommonService<E> {
    R repository;

    public AbstractService(R repository) {
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
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<E> getAll() {
        return repository.findAll();
    }

    public boolean isExists(E entity) {
        ExampleMatcher modelMatcher = ExampleMatcher.matching()
                .withIgnorePaths("id", "createdDate", "updatedBy", "updatedDate");
        Example<E> example = Example.of(entity, modelMatcher);
        return repository.exists(example);
    }
}