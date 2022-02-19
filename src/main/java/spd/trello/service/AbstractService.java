package spd.trello.service;

import spd.trello.domain.parent_classes.Resource;
import spd.trello.exception.InvalidRequestException;
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
        try {
            return repository.save(entity);
        } catch (Exception e) {
            throw new InvalidRequestException();
        }
    }

    @Override
    public E update(E entity) {
        try {
            return repository.save(entity);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @Override
    public void deleteById(UUID id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @Override
    public E getById(UUID id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<E> getAll() {
        return repository.findAll();
    }

}