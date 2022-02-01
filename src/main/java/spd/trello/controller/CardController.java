package spd.trello.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spd.trello.domain.Card;
import spd.trello.service.CardService;

import java.util.List;

@RestController
@RequestMapping(value = "/cards", produces = MediaType.APPLICATION_JSON_VALUE)
public class CardController extends AbstractController<Card, CardService> {

    public CardController(CardService service) {
        super(service);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Card> readByName(@PathVariable String name) {
        List<Card> result = service.findByName(name);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}