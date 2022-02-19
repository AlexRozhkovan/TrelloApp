package spd.trello.service;

import org.springframework.stereotype.Service;
import spd.trello.domain.Card;
import spd.trello.repository.CardRepository;

@Service
public class CardService extends AbstractService<Card, CardRepository> {

    public CardService(CardRepository repository) {
        super(repository);
    }
}