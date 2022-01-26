package spd.trello.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import spd.trello.domain.Workspace;

import java.util.List;
import java.util.UUID;

@Repository
public class WorkspaceRepository implements IRepository<Workspace> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public WorkspaceRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final String save = "insert into workspaces(id, created_by, created_date, name, description, visibility) values (?,?,?,?,?,?) " +
            "RETURNING id, created_by, created_date, name, description, visibility";
    private final String findById = "SELECT * FROM workspaces WHERE id=?";
    private final String findAllById = "SELECT * FROM workspaces";
    private final String update = "UPDATE workspaces SET updated_by =?, updated_date =?, name =?, description =?, visibility =? WHERE id =?";
    private final String deleteById = "DELETE FROM workspaces WHERE id=?";

    @Override
    public Workspace findById(UUID id) {
        return jdbcTemplate.queryForObject(findById, new BeanPropertyRowMapper<>(Workspace.class), id);
    }

    @Override
    public List<Workspace> findAll() {
        return jdbcTemplate.query(findAllById, new BeanPropertyRowMapper<>(Workspace.class));
    }

    @Override
    public Workspace create(Workspace entity) {
        return jdbcTemplate.queryForObject(save,
                new BeanPropertyRowMapper<>(Workspace.class),
                entity.getId(),
                entity.getCreatedBy(),
                entity.getCreatedDate(),
                entity.getName(),
                entity.getDescription(),
                entity.getVisibility().toString());
    }

    @Override
    public void update(Workspace entity) {
        jdbcTemplate.update(update,
                entity.getUpdatedBy(),
                entity.getUpdatedDate(),
                entity.getName(),
                entity.getDescription(),
                entity.getVisibility().toString(),
                entity.getId());
    }

    @Override
    public boolean deleteByID(UUID id) {
        return jdbcTemplate.update(deleteById, id) > 0;
    }
}
