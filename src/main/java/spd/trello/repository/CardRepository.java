package spd.trello.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import spd.trello.domain.Card;

import java.util.List;
import java.util.UUID;

@Repository
public class CardRepository implements IRepository<Card> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final String save = "insert into cards(id, created_by, created_date, name, description, archived, cardList_id) values (?,?,?,?,?,?,?)" +
            "RETURNING id, created_by, created_date, name, description, archived, cardList_id";
    private final String findById = "SELECT * FROM cards WHERE id=?";
    private final String findAllById = "SELECT * FROM cards";
    private final String update = "UPDATE cards SET updated_by=?,updated_date=?, name=?, description=?, archived=?, cardlist_id=? WHERE id =?";
    private final String deleteById = "DELETE FROM cards WHERE id=? ";

    @Override
    public Card findById(UUID id) {
        return jdbcTemplate.queryForObject(findById, new BeanPropertyRowMapper<>(Card.class), id);
    }

    @Override
    public List<Card> findAll() {
        return jdbcTemplate.query(findAllById, new BeanPropertyRowMapper<>(Card.class));
    }

    @Override
    public Card create(Card entity) {
        return jdbcTemplate.queryForObject(save,
                new BeanPropertyRowMapper<>(Card.class),
                entity.getId(),
                entity.getCreatedBy(),
                entity.getCreatedDate(),
                entity.getName(),
                entity.getDescription(),
                entity.getArchived(),
                entity.getCardListId());
    }

    @Override
    public void update(Card entity) {
        jdbcTemplate.update(update,
                entity.getUpdatedBy(),
                entity.getUpdatedDate(),
                entity.getName(),
                entity.getDescription(),
                entity.getArchived(),
                entity.getCardListId(),
                entity.getId());
    }

    @Override
    public boolean deleteByID(UUID id) {
        return jdbcTemplate.update(deleteById, id) > 0;
    }
}
