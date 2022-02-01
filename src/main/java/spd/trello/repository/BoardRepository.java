package spd.trello.repository;

import org.springframework.stereotype.Repository;
import spd.trello.domain.Board;

import java.util.List;

@Repository
public interface BoardRepository extends IRepository<Board> {

    List<Board> findByName(String name);
}