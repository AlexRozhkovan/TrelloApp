package spd.trello.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spd.trello.domain.parent_classes.Resource;
import spd.trello.exception.ResourceNotFoundException;
import spd.trello.service.CommonService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class AbstractController<E extends Resource, S extends CommonService<E>> implements CommonController<E> {
    S service;

    public AbstractController(S service) {
        this.service = service;
    }


    @PostMapping
    @Override
    public ResponseEntity<E> create(@RequestBody E resource) {
        E result = service.save(resource);
        return new ResponseEntity(result, HttpStatus.CREATED);
    }


    @PutMapping("/id/{id}")
    @Override
    public ResponseEntity<E> update(@PathVariable UUID id, @RequestBody E resource) {
        E entity = service.getById(id);
        if (entity == null) throw new ResourceNotFoundException();
        resource.setUpdatedDate(LocalDateTime.now());
        E result = service.update(resource);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    @Override
    public HttpStatus deleteById(@PathVariable UUID id) {
        service.deleteById(id);
        return HttpStatus.OK;
    }

    @GetMapping("/id/{id}")
    @Override
    public ResponseEntity<E> readById(@PathVariable UUID id) {
        E result = service.getById(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping
    @Override
    public List<E> readAll() {
        return service.getAll();
    }
}
