package spd.trello.repository;

import org.springframework.stereotype.Repository;
import spd.trello.domain.Card;

import java.util.List;

@Repository
public interface CardRepository extends IRepository<Card> {

    List<Card> findByName(String name);
}