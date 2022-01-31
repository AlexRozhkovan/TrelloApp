package spd.trello.service;

import org.springframework.stereotype.Service;
import spd.trello.domain.Card;
import spd.trello.domain.CardList;
import spd.trello.repository.CardListRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CardListService extends AbstractService<CardList, CardListRepository> {

    public CardListService(CardListRepository repository) {
        super(repository);
    }

    public CardList create(CardList entity) {
        entity.getCards().forEach(card -> card.setCardList(entity));
        return super.save(entity);
    }

    public CardList update(CardList entity) {
        return super.update(entity);
    }

    public List<CardList> findAll() {
        return super.getAll();
    }

    public CardList findByID(UUID id) {
        return super.getById(id);
    }

    public void deleteByID(UUID id) {
        super.deleteById(id);
    }
}
