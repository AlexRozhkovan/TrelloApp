package spd.trello.service;

import org.springframework.stereotype.Service;
import spd.trello.domain.Label;
import spd.trello.repository.LabelRepository;

import java.util.List;
import java.util.UUID;

@Service
public class LabelService extends AbstractService<Label, LabelRepository> {

    public LabelService(LabelRepository repository) {
        super(repository);
    }

    public Label create(Label entity) {
        return super.save(entity);
    }

    public Label update(Label entity) {
        return super.update(entity);
    }

    public List<Label> findAll() {
        return super.getAll();
    }

    public Label findByID(UUID id) {
        return super.getById(id);
    }

    public void deleteByID(UUID id) {
        super.deleteById(id);
    }
}