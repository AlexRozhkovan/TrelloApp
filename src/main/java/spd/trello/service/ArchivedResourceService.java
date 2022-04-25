package spd.trello.service;

import spd.trello.domain.parent_classes.ArchivedResource;
import spd.trello.repository.CommonRepository;

import java.time.LocalDateTime;

public class ArchivedResourceService<E extends ArchivedResource, R extends CommonRepository<E>>
        extends AbstractService<E, R> {

    public ArchivedResourceService(R repository) {
        super(repository);
    }

    @Override
    public E update(E entity) {
        E foundEntity = repository.findById(entity.getId()).get();
        entity = checkArchivedResource(entity, foundEntity);
        return repository.save(entity);
    }

    private E checkArchivedResource(E entity, E foundEntity) {
        if (foundEntity.getArchived() && entity.getArchived())
            throw new IllegalArgumentException(entity.getClass().getSimpleName() + " can't be updated because it archived");
        else if ((!foundEntity.getArchived() && entity.getArchived())
                || (foundEntity.getArchived() && !entity.getArchived())) {
            foundEntity.setArchived(entity.getArchived());
            return foundEntity;
        }
        entity.setUpdatedDate(LocalDateTime.now());
        entity.setCreatedDate(foundEntity.getCreatedDate());
        entity.setCreatedBy(foundEntity.getCreatedBy());
        return entity;
    }
}