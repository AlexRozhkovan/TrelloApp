package spd.trello.service;

import spd.trello.domain.parent_classes.Resource;
import spd.trello.exception.NotFoundException;
import spd.trello.repository.CommonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class AbstractService<E extends Resource, R extends CommonRepository<E>> implements CommonService<E> {

    R repository;

    public AbstractService(R repository) {
        this.repository = repository;
    }

    @Override
    public E create(E entity) {
        entity.setCreatedDate(LocalDateTime.now());
        return repository.save(entity);
    }

    @Override
    public E update(E entity) {
        readById(entity.getId());
        entity.setUpdatedDate(LocalDateTime.now());
        return repository.save(entity);
    }

    @Override
    public Page<E> readAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public E readById(UUID id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public E delete(UUID id) {
        E result = readById(id);
        repository.deleteById(id);
        return result;
    }
}