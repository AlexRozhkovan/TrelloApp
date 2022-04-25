package spd.trello.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import spd.trello.domain.parent_classes.Resource;

import java.util.UUID;

public interface CommonService<E extends Resource> {
    E create(E entity);

    E update(E entity);

    Page<E> readAll(Pageable pageable);

    E readById(UUID id);

    E delete(UUID id);
}