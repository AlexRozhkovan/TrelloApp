package spd.trello.service;

import spd.trello.domain.Board;
import spd.trello.domain.Card;
import spd.trello.repository.CardRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class CardService extends AbstractService<Card> {
    public CardService(CardRepository repository) {
        super(repository);
    }

    public Card create(String createdBy, String name, String description, UUID cardListId) {
        Card card = new Card();
        card.setCreatedBy(createdBy);
        card.setName(name);
        card.setDescription(description);
        card.setCardListId(cardListId);
        repository.create(card);
        return card;
    }

    public Card update(UUID id, String updatedBy, String name, String description, Boolean archived) {
        Card byID = repository.findById(id);
        byID.setUpdatedBy(updatedBy);
        byID.setName(name);
        byID.setDescription(description);
        byID.setArchived(archived);
        byID.setUpdatedDate(LocalDateTime.now());
        return repository.update(byID);
    }

    public List<Card> findAll() {
        return repository.findAll();
    }

    public Card findByID(UUID id) {
        return repository.findById(id);
    }

    public boolean deleteAll() {
        return repository.deleteAll();
    }

    public boolean deleteByID(UUID id) {
        return repository.deleteByID(id);
    }
}

