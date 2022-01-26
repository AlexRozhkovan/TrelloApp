package spd.trello.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import spd.trello.domain.Board;

import java.util.List;
import java.util.UUID;

@Repository
public class BoardRepository implements IRepository<Board> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String save = "insert into boards(id, created_by, created_date, name, description, archived, visibility, workspace_id) values (?,?,?,?,?,?,?,?)" +
            "RETURNING id, created_by, created_date, name, description, archived, visibility, workspace_id";
    private static final String findById = "SELECT * FROM boards WHERE id=?";
    private static final String findAllById = "SELECT * FROM boards";
    private static final String update = "UPDATE boards SET updated_by=?,updated_date=?, name=?, description=?, archived=?, visibility=? WHERE id =?";
    private static final String deleteById = "DELETE FROM boards WHERE id=? ";

    @Override
    public Board findById(UUID id) {
        return jdbcTemplate.queryForObject(findById, new BeanPropertyRowMapper<>(Board.class), id);
    }

    @Override
    public List<Board> findAll() {
        return jdbcTemplate.query(findAllById, new BeanPropertyRowMapper<>(Board.class));
    }

    @Override
    public Board create(Board entity) {
        return jdbcTemplate.queryForObject(save,
                new BeanPropertyRowMapper<>(Board.class),
                entity.getId(),
                entity.getCreatedBy(),
                entity.getCreatedDate(),
                entity.getName(),
                entity.getDescription(),
                entity.getArchived(),
                entity.getVisibility().toString(),
                entity.getWorkspaceId());
    }

    @Override
    public void update(Board entity) {
        jdbcTemplate.update(update,
                entity.getCreatedBy(),
                entity.getUpdatedDate(),
                entity.getName(),
                entity.getDescription(),
                entity.getArchived(),
                entity.getVisibility().toString(),
                entity.getId());
    }

    public boolean deleteByID(UUID id) {
        return jdbcTemplate.update(deleteById, id) > 0;
    }
}

