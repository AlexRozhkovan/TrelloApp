package spd.trello.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spd.trello.domain.CardList;
import spd.trello.service.CardListService;

import java.util.List;

@RestController
@RequestMapping(value = "/cardlists", produces = MediaType.APPLICATION_JSON_VALUE)
public class CardListController extends AbstractController<CardList, CardListService> {

    public CardListController(CardListService service) {
        super(service);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<CardList> readByName(@PathVariable String name) {
        List<CardList> result = service.findByName(name);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}