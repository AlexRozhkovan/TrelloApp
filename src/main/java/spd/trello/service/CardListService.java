package spd.trello.service;

import org.springframework.stereotype.Service;
import spd.trello.domain.CardList;
import spd.trello.repository_jpa.CardListRepository;

@Service
public class CardListService extends ArchivedResourceService<CardList, CardListRepository> {
    public CardListService(CardListRepository repository) {
        super(repository);
    }
}