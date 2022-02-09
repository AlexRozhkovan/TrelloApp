package spd.trello.service;

import org.springframework.stereotype.Service;
import spd.trello.domain.Board;
import spd.trello.repository.BoardRepository;

import java.util.List;
import java.util.UUID;

@Service
public class BoardService extends AbstractService<Board, BoardRepository> {

    public BoardService(BoardRepository repository) {
        super(repository);
    }

    public Board create(Board entity) {
        return super.save(entity);
    }

    public Board update(Board entity) {
        return super.update(entity);
    }

    public List<Board> findAll() {
        return super.getAll();
    }

    public Board findByID(UUID id) {
        return super.getById(id);
    }

    public void deleteByID(UUID id) {
        super.deleteById(id);
    }

    public List<Board> findByName(String name) {
        return repository.findByName(name);
    }

}
