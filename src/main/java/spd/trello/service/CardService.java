package spd.trello.service;

import org.springframework.stereotype.Service;
import spd.trello.domain.Card;
import spd.trello.repository.CardRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
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
        return repository.create(card);
    }

    public Card update(UUID id, String updatedBy, String name, String description, Boolean archived) {
        Card byID = repository.findById(id);
        byID.setUpdatedBy(updatedBy);
        byID.setName(name);
        byID.setDescription(description);
        byID.setArchived(archived);
        byID.setUpdatedDate(LocalDateTime.now());
        repository.update(byID);
        return byID;
    }

    public List<Card> findAll() {
        return repository.findAll();
    }

    public Card findByID(UUID id) {
        return repository.findById(id);
    }

    public boolean deleteByID(UUID id) {
        return repository.deleteByID(id);
    }
}

