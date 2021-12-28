package spd.trello.service;

import spd.trello.domain.*;
import spd.trello.repository.CardRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class CardService extends AbstractService<Card> {
    public CardService(CardRepository repository) {
        super(repository);
    }

    @Override
    public Card create(Scanner scanner) {
        Card card = new Card();
        System.out.println("Enter card name");
        card.setName(scanner.nextLine());
        System.out.println("Enter card description");
        card.setDescription(scanner.nextLine());
        repository.create(card);
        return card;
    }

    @Override
    public List<Card> findAll() {
        return repository.findAll();
    }

    @Override
    public void print(Card entity) {
        System.out.println(entity);
    }
}

