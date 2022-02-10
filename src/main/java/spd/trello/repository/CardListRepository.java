package spd.trello.repository;

import org.springframework.stereotype.Repository;
import spd.trello.domain.CardList;

import java.util.List;

@Repository
public interface CardListRepository extends IRepository<CardList> {

    List<CardList> findByName(String name);
}