package spd.trello.service;

import org.springframework.stereotype.Service;
import spd.trello.domain.CardList;
import spd.trello.repository.CardListRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CardListService extends AbstractService<CardList> {

    public CardListService(CardListRepository repository) {
        super(repository);
    }

    public CardList create(String createdBy, String name, UUID boardId) {
        CardList cardList = new CardList();
        cardList.setCreatedBy(createdBy);
        cardList.setName(name);
        cardList.setBoardId(boardId);
        return repository.create(cardList);
    }

    public CardList update(UUID id, String updatedBy, String name) {
        CardList byID = repository.findById(id);
        byID.setUpdatedBy(updatedBy);
        byID.setName(name);
        byID.setUpdatedDate(LocalDateTime.now());
        repository.update(byID);
        return byID;
    }

    public List<CardList> findAll() {
        return repository.findAll();
    }

    public CardList findByID(UUID id) {
        return repository.findById(id);
    }

    public boolean deleteByID(UUID id) {
        return repository.deleteByID(id);
    }
}
