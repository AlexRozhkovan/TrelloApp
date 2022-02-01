package spd.trello.service;

import spd.trello.domain.parent_classes.Resource;

import java.util.List;
import java.util.UUID;

public interface CommonService<E extends Resource> {
    E save(E entity);

    E update(E entity);

    void deleteById(UUID id);

    E getById(UUID id);

    List<E> getAll();
}