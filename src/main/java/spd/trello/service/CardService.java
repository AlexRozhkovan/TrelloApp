package spd.trello.service;

import spd.trello.domain.Card;
import spd.trello.repository.CardRepository;

import java.util.List;
import java.util.UUID;

public class CardService extends AbstractService<Card> {
    public CardService(CardRepository repository) {
        super(repository);
    }

    public Card create(String name, String description) {
        Card card = new Card();
        card.setId(UUID.randomUUID());
        card.setName(name);
        card.setDescription(description);
        repository.create(card);
        return card;
    }

    public boolean deleteAll() {
        repository.deleteAll();
        return true;
    }

    public Card update(UUID id) {
        Card byID = repository.findByID(id);
        repository.update(byID);
        return byID;
    }

    public List<Card> findAll() {
        return repository.findAll();
    }

    public void print(Card entity) {
        System.out.println(entity);
    }

    public Card findByID(UUID id) {
        return repository.findByID(id);
    }

    public boolean deleteByID(UUID id) {
        repository.deleteByID(id);
        return true;
    }
}

