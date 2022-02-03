package spd.trello.service;

import org.springframework.stereotype.Service;
import spd.trello.domain.CheckList;
import spd.trello.repository.CheckListRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CheckListService extends AbstractService<CheckList, CheckListRepository> {

    public CheckListService(CheckListRepository repository) {
        super(repository);
    }

    public CheckList create(CheckList entity) {
        return super.save(entity);
    }

    public CheckList update(CheckList entity) {
        return super.update(entity);
    }

    public List<CheckList> findAll() {
        return super.getAll();
    }

    public CheckList findByID(UUID id) {
        return super.getById(id);
    }

    public void deleteByID(UUID id) {
        super.deleteById(id);
    }
}