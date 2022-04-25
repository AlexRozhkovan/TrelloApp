package spd.trello.service;

import spd.trello.domain.Comment;
import spd.trello.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService extends AbstractService<Comment, CommentRepository> {
    public CommentService(CommentRepository repository) {
        super(repository);
    }
}