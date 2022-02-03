package spd.trello.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spd.trello.domain.Comment;
import spd.trello.service.CommentService;

@RestController
@RequestMapping(value = "/comments", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController extends AbstractController<Comment, CommentService> {

    public CommentController(CommentService service) {
        super(service);
    }
}