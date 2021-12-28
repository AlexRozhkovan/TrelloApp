package spd.trello.service;

import spd.trello.domain.CardList;
import spd.trello.repository.CardListRepository;

import java.util.List;
import java.util.Scanner;

public class CardListService extends AbstractService<CardList> {
    public CardListService(CardListRepository repository) {
        super(repository);
    }

    @Override
    public CardList create(Scanner scanner) {
        CardList cardList = new CardList();
        System.out.println("Enter cardList name");
        cardList.setName(scanner.nextLine());
        repository.create(cardList);
        return cardList;
    }

    @Override
    public List<CardList> findAll() {
        return repository.findAll();
    }

    @Override
    public void print(CardList entity) {
        System.out.println(entity);
    }
}
