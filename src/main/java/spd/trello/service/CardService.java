package spd.trello.service;

import org.springframework.stereotype.Service;
import spd.trello.domain.Card;
import spd.trello.repository.CardRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CardService extends AbstractService<Card, CardRepository> {

    public CardService(CardRepository repository) {
        super(repository);
    }

    public Card create(Card entity) {
        entity.getComments().forEach(comment -> comment.setCard(entity));
        entity.getAttachments().forEach(attachment -> attachment.setCard(entity));
        return super.save(entity);
    }

    public Card update(Card entity) {
        return super.update(entity);
    }

    public List<Card> findAll() {
        return super.getAll();
    }

    public Card findByID(UUID id) {
        return super.getById(id);
    }

    public void deleteByID(UUID id) {
        super.deleteById(id);
    }

    public List<Card> findByName(String name) {
        return repository.findByName(name);
    }
}