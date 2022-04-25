package spd.trello.service;

import spd.trello.domain.Card;
import spd.trello.repository_jpa.CardRepository;
import org.springframework.stereotype.Service;

@Service
public class CardService extends ArchivedResourceService<Card, CardRepository> {
    public CardService(CardRepository repository) {
        super(repository);
    }
}