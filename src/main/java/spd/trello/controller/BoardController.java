package spd.trello.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spd.trello.domain.Board;
import spd.trello.service.BoardService;

import java.util.List;

@RestController
@RequestMapping(value = "/boards", produces = MediaType.APPLICATION_JSON_VALUE)
public class BoardController extends AbstractController<Board, BoardService> {

    public BoardController(BoardService service) {
        super(service);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Board> readByName(@PathVariable String name) {
        List<Board> result = service.findByName(name);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
