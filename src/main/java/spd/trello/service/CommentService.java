package spd.trello.service;

import org.springframework.stereotype.Service;
import spd.trello.domain.Comment;
import spd.trello.repository.CommentRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService extends AbstractService<Comment, CommentRepository> {

    public CommentService(CommentRepository repository) {
        super(repository);
    }

    public List<Comment> findAllByCard(UUID id) {
        return repository.findCommentsByCardId(id);
    }
}
