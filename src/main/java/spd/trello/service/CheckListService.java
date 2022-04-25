package spd.trello.service;

import spd.trello.domain.CheckList;
import spd.trello.repository.CheckListRepository;
import org.springframework.stereotype.Service;

@Service
public class CheckListService extends AbstractService<CheckList, CheckListRepository> {
    public CheckListService(CheckListRepository repository) {
        super(repository);
    }
}