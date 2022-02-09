package spd.trello.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spd.trello.domain.Board;
import spd.trello.service.BoardService;

@RestController
@RequestMapping(value = "/boards", produces = MediaType.APPLICATION_JSON_VALUE)
public class BoardController extends AbstractController<Board, BoardService> {

    public BoardController(BoardService service) {
        super(service);
    }
}
