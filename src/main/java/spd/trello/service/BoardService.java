package spd.trello.service;

import org.springframework.stereotype.Service;
import spd.trello.domain.Board;
import spd.trello.repository_jpa.BoardRepository;

@Service
public class BoardService extends ArchivedResourceService<Board, BoardRepository> {
    public BoardService(BoardRepository repository) {
        super(repository);
    }
}