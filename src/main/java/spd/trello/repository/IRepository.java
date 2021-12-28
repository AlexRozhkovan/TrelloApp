package spd.trello.repository;

import java.util.List;
import java.util.UUID;

public interface IRepository <T>{
    T findByID(UUID id);
    void create(T entity);
    //T update(T entity);
    //boolean delete(UUID id);
    public List<T> findAll();
}
