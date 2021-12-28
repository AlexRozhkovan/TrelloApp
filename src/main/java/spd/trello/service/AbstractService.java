package spd.trello.service;

import spd.trello.repository.IRepository;

import java.util.List;
import java.util.Scanner;

public abstract class AbstractService<T> {
    
    protected IRepository<T> repository;

    public AbstractService(IRepository<T> repository) {
        this.repository = repository;
    }
    public abstract T create(Scanner scanner);

    public abstract List<T> findAll();

    public abstract void print(T entity);

}
