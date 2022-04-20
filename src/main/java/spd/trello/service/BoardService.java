package spd.trello.service;

import org.springframework.stereotype.Service;
import spd.trello.domain.Board;
import spd.trello.repository.BoardRepository;

import java.util.List;
import java.util.UUID;

@Service
public class BoardService extends ArchivedResourceService<Board, BoardRepository> {

    public BoardService(BoardRepository repository) {
        super(repository);
    }

    public List<Board> findAllByWorkspace(UUID id) {
        return repository.findBoardsByWorkspaceId(id);
    }
}