package spd.trello.repository_jpa;

import org.springframework.stereotype.Repository;
import spd.trello.domain.Board;

@Repository
public interface BoardRepository extends CommonRepository<Board> {
}