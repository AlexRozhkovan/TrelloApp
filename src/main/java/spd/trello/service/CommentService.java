package spd.trello.service;

import org.springframework.stereotype.Service;
import spd.trello.domain.Comment;
import spd.trello.repository.CommentRepository;

@Service
public class CommentService extends AbstractService<Comment, CommentRepository> {

    public CommentService(CommentRepository repository) {
        super(repository);
    }
}