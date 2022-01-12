package spd.trello.service;

import spd.trello.domain.Board;
import spd.trello.domain.enumerations.BoardVisibility;
import spd.trello.repository.BoardRepository;

import java.util.List;
import java.util.UUID;

public class BoardService extends AbstractService<Board> {
    public BoardService(BoardRepository repository) {
        super(repository);
    }

    public Board create(String name, String description) {
        Board board = new Board();
        board.setId(UUID.randomUUID());
        board.setName(name);
        board.setDescription(description);
        repository.create(board);
        return board;
    }

    public boolean deleteAll() {
        repository.deleteAll();
        return true;
    }

    public Board update(UUID id) {
        Board byID = repository.findByID(id);
        repository.update(byID);
        return byID;
    }

    public List<Board> findAll() {
        return repository.findAll();
    }

    public void print(Board entity) {
        System.out.println(entity);
    }

    public Board findByID(UUID id) {
        return repository.findByID(id);
    }

    public boolean deleteByID(UUID id) {
        repository.deleteByID(id);
        return true;
    }
}
