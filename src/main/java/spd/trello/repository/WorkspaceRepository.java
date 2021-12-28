package spd.trello.repository;


import spd.trello.domain.Card;
import spd.trello.domain.Workspace;
import spd.trello.repository.mapper.CardMapper;
import spd.trello.repository.mapper.WorkspaceMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WorkspaceRepository implements IRepository<Workspace>{

    private final DataSource dataSource;
    private final String saveSTMT = "insert into workspaces(id, name, description, visibility) values (?,?,?,?)";
    private final String findByIDSTMT = "SELECT * FROM workspaces WHERE id=?";
    private final String findAllByIDSTMT = "SELECT * FROM workspaces";

    public WorkspaceRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Workspace findByID(UUID id){
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(findByIDSTMT)){
            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                WorkspaceMapper workspaceMapper = new WorkspaceMapper();
                return workspaceMapper.extractFromResultSet(resultSet);
            }
        }catch (SQLException e){
            throw new IllegalStateException("WorkspaceRepository::findByID failed");
        }
        throw new IllegalStateException("Workspace with ID: " + id.toString() + "doesn't exist");
    }

    public List<Workspace> findAll() {
        List<Workspace> result = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(findAllByIDSTMT)){
            ResultSet resultSet;
            resultSet = statement.executeQuery();
            WorkspaceMapper workspaceMapper = new WorkspaceMapper();
            while (resultSet.next()) {
                result.add(workspaceMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public void create(Workspace entity) {

        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement(saveSTMT)) {
            stmt.setObject(1, entity.getId());
            stmt.setString(2, entity.getName());
            stmt.setString(3, entity.getDescription());
            stmt.setString(4, entity.getVisibility().toString());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Workspace creation failed", e);
        }
    }
}
