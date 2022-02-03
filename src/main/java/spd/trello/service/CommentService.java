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

    public Comment create(Comment entity) {
        return super.save(entity);
    }

    public Comment update(Comment entity) {
        return super.update(entity);
    }

    public List<Comment> findAll() {
        return super.getAll();
    }

    public Comment findByID(UUID id) {
        return super.getById(id);
    }

    public void deleteByID(UUID id) {
        super.deleteById(id);
    }
}