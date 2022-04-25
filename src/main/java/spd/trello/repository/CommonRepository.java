package spd.trello.repository;

import spd.trello.domain.parent_classes.Domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface CommonRepository<E extends Domain> extends JpaRepository<E, UUID>,
        JpaSpecificationExecutor<E> {
    void deleteById(UUID id);
}