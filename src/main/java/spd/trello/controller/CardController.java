package spd.trello.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spd.trello.domain.Card;
import spd.trello.domain.CardList;
import spd.trello.service.CardListService;
import spd.trello.service.CardService;

@RestController
@RequestMapping(value = "/cards", produces = MediaType.APPLICATION_JSON_VALUE)
public class CardController extends AbstractController<Card, CardService> {

    public CardController(CardService service) {
        super(service);
    }
}