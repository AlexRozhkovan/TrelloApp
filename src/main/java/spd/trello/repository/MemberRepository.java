package spd.trello.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import spd.trello.domain.Member;

import java.util.List;
import java.util.UUID;

@Repository
public class MemberRepository implements IRepository<Member> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final String save = "insert into members(id, created_by, created_date, role, user_id) values (?,?,?,?,?)" +
            "RETURNING id, created_by, created_date, role, user_id";
    private final String findById = "SELECT * FROM members WHERE id=?";
    private final String findAllById = "SELECT * FROM members";
    private final String update = "UPDATE members SET updated_by=?, updated_date=?, role=? WHERE id =?";
    private final String deleteById = "DELETE FROM members WHERE id=? ";

    @Override
    public Member findById(UUID id) {
        return jdbcTemplate.queryForObject(findById, new BeanPropertyRowMapper<>(Member.class), id);
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query(findAllById, new BeanPropertyRowMapper<>(Member.class));
    }

    @Override
    public Member create(Member entity) {
        return jdbcTemplate.queryForObject(save,
                new BeanPropertyRowMapper<>(Member.class),
                entity.getId(),
                entity.getCreatedBy(),
                entity.getCreatedDate(),
                entity.getRole().toString(),
                entity.getUser());
    }

    @Override
    public void update(Member entity) {
        jdbcTemplate.update(update,
                entity.getUpdatedBy(),
                entity.getUpdatedDate(),
                entity.getRole().toString(),
                entity.getId());
    }

    @Override
    public boolean deleteByID(UUID id) {
        return jdbcTemplate.update(deleteById, id) > 0;
    }

}
