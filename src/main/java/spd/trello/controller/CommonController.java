package spd.trello.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import spd.trello.domain.parent_classes.Resource;

import java.util.UUID;

public interface CommonController<E extends Resource> {

    ResponseEntity<E> create(E resource);

    ResponseEntity<E> update(UUID id, E resource);

    ResponseEntity<E> readById(UUID id);

    ResponseEntity<E> delete(UUID id);

    Page<E> readAll(Pageable pageable);
}
