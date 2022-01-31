package spd.trello.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spd.trello.domain.Board;
import spd.trello.domain.CardList;
import spd.trello.service.BoardService;
import spd.trello.service.CardListService;

@RestController
@RequestMapping(value = "/cardlists", produces = MediaType.APPLICATION_JSON_VALUE)
public class CardListController extends AbstractController<CardList, CardListService> {

    public CardListController(CardListService service) {
        super(service);
    }
}