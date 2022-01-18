package spd.trello.repository;

import java.util.List;
import java.util.UUID;

public interface IRepository<T> {
    T findById(UUID id);

    T create(T entity);

    List<T> findAll();

    T update(T entity);

    default boolean deleteAll() {
        return false;
    }

    default boolean deleteByID(UUID id) {
        return false;
    }
}

