package spd.trello.repository_jpa;

import spd.trello.domain.Card;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends CommonRepository<Card> {
}