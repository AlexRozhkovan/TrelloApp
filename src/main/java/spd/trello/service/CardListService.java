package spd.trello.service;

import spd.trello.domain.CardList;
import spd.trello.repository.CardListRepository;

import java.util.List;
import java.util.UUID;

public class CardListService extends AbstractService<CardList> {
    public CardListService(CardListRepository repository) {
        super(repository);
    }


    public CardList create(String name) {
        CardList cardList = new CardList();
        cardList.setId(UUID.randomUUID());
        cardList.setName(name);

        repository.create(cardList);
        return cardList;
    }

    public boolean deleteAll() {
        repository.deleteAll();
        return true;
    }

    public CardList update(UUID id, String name, Boolean archived) {
        CardList byID = repository.findByID(id);
        byID.setName(name);
        byID.setArchived(archived);
        repository.update(byID);
        return byID;
    }

    public List<CardList> findAll() {
        return repository.findAll();
    }

    public void print(CardList entity) {
        System.out.println(entity);
    }

    public CardList findByID(UUID id) {
        return repository.findByID(id);
    }

    public boolean deleteByID(UUID id) {
        repository.deleteByID(id);
        return true;
    }
}
