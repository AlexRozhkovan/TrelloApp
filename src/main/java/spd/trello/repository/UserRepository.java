package spd.trello.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import spd.trello.domain.User;

import java.util.List;
import java.util.UUID;

@Repository
public class UserRepository implements IRepository<User> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final String save = "insert into users(id, created_date, first_name, last_name, email) values (?,?,?,?,?)" +
            "RETURNING id, created_date, first_name, last_name, email";
    private final String findById = "SELECT * FROM users WHERE id=?";
    private final String findAllById = "SELECT * FROM users";
    private final String update = "UPDATE users SET updated_date=?, first_name=?, last_name=?, email=? WHERE id =?";
    private final String deleteById = "DELETE FROM users WHERE id=?";

    @Override
    public User findById(UUID id) {
        return jdbcTemplate.queryForObject(findById, new BeanPropertyRowMapper<>(User.class), id);
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(findAllById, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User create(User entity) {
        return jdbcTemplate.queryForObject(save,
                new BeanPropertyRowMapper<>(User.class),
                entity.getId(),
                entity.getCreatedDate(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail());
    }

    @Override
    public void update(User entity) {
        jdbcTemplate.update(update,
                entity.getUpdatedDate(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getId());
    }

    @Override
    public boolean deleteByID(UUID id) {
        return jdbcTemplate.update(deleteById, id) > 0;
    }
}
