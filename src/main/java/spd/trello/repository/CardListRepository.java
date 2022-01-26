package spd.trello.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import spd.trello.domain.CardList;

import java.util.List;
import java.util.UUID;

@Repository
public class CardListRepository implements IRepository<CardList> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CardListRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final String save = "insert into cardLists(id, created_by, created_date, name, archived, board_id) values (?,?,?,?,?,?)" +
            "RETURNING id, created_by, created_date, name, archived, board_id";
    private final String findById = "SELECT * FROM cardLists WHERE id=?";
    private final String findAllById = "SELECT * FROM cardLists";
    private final String update = "UPDATE cardLists SET updated_by =?, updated_date =?, name =?, archived=?, board_id=? WHERE id =?";
    private final String deleteById = "DELETE FROM cardLists WHERE id=? ";

    @Override
    public CardList findById(UUID id) {
        return jdbcTemplate.queryForObject(findById, new BeanPropertyRowMapper<>(CardList.class), id);
    }

    public List<CardList> findAll() {
        return jdbcTemplate.query(findAllById, new BeanPropertyRowMapper<>(CardList.class));
    }


    @Override
    public CardList create(CardList entity) {
        return jdbcTemplate.queryForObject(save,
                new BeanPropertyRowMapper<>(CardList.class),
                entity.getId(),
                entity.getCreatedBy(),
                entity.getCreatedDate(),
                entity.getName(),
                entity.getArchived(),
                entity.getBoardId());
    }

    @Override
    public void update(CardList entity) {
        jdbcTemplate.update(update,
                entity.getUpdatedBy(),
                entity.getUpdatedDate(),
                entity.getName(),
                entity.getArchived(),
                entity.getBoardId(),
                entity.getId());
    }

    @Override
    public boolean deleteByID(UUID id) {
        return jdbcTemplate.update(deleteById, id) > 0;
    }
}
