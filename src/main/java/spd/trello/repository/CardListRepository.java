package spd.trello.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import spd.trello.domain.CardList;
import spd.trello.domain.Workspace;

import java.util.List;
import java.util.UUID;

@Repository
public interface CardListRepository extends IRepository<CardList> {

    List<CardList> findByName(String name);
}