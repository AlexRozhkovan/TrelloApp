package spd.trello.repository;

import spd.trello.domain.Card;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CommonRepository<Card> {
}