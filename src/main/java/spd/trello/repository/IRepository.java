package spd.trello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import spd.trello.domain.parent_classes.Resource;

import java.util.UUID;

@NoRepositoryBean
public interface IRepository<E extends Resource> extends JpaRepository<E, UUID> {
}