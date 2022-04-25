package spd.trello.service;

import spd.trello.domain.Comment;
import spd.trello.repository_jpa.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentService extends AbstractService<Comment, CommentRepository> {
    public CommentService(CommentRepository repository) {
        super(repository);
    }
}