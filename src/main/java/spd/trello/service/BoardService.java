package spd.trello.service;

import org.springframework.stereotype.Service;
import spd.trello.domain.Board;
import spd.trello.domain.enumerations.BoardVisibility;
import spd.trello.repository.BoardRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService extends AbstractService<Board> {

    public BoardService(BoardRepository repository) {
        super(repository);
    }

    public Board create(String createdBy, String name, String description, BoardVisibility visibility, UUID workspaceId) {
        Board board = new Board();
        board.setCreatedBy(createdBy);
        board.setName(name);
        board.setDescription(description);
        board.setVisibility(visibility);
        board.setWorkspaceId(workspaceId);
        return repository.create(board);
    }

    public Board update(UUID id, String updatedBy, String name, String description, Boolean archived, BoardVisibility visibility) {
        Board byID = repository.findById(id);
        byID.setUpdatedBy(updatedBy);
        byID.setName(name);
        byID.setDescription(description);
        byID.setArchived(archived);
        byID.setVisibility(visibility);
        byID.setUpdatedDate(LocalDateTime.now());
        repository.update(byID);
        return byID;
    }

    public List<Board> findAll() {
        return repository.findAll();
    }

    public Board findByID(UUID id) {
        return repository.findById(id);
    }

    public boolean deleteByID(UUID id) {
        return repository.deleteByID(id);
    }
}
