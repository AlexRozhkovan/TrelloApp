package spd.trello.repository;

import java.util.List;
import java.util.UUID;

public interface IRepository<T> {
    T findById(UUID id);

    T create(T entity);

    List<T> findAll();

    void update(T entity);

    boolean deleteByID(UUID id);
}

