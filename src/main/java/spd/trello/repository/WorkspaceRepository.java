package spd.trello.repository;

import spd.trello.domain.Member;
import spd.trello.domain.Workspace;
import spd.trello.repository.mapper.MemberMapper;
import spd.trello.repository.mapper.WorkspaceMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WorkspaceRepository implements IRepository<Workspace> {

    private final DataSource dataSource;
    private final String saveSTMT = "insert into workspaces(id, created_by, created_date, name, description, visibility) values (?,?,?,?,?,?)";
    private final String findByIDSTMT = "SELECT * FROM workspaces WHERE id=?";
    private final String findAllByIDSTMT = "SELECT * FROM workspaces";
    private final String updateSTMT = "UPDATE workspaces SET updated_by =?, updated_date =?, name =?, description =?, visibility =? WHERE id =?";
    private final String deleteByIDSTMT = "DELETE FROM workspaces WHERE id=?";
    private final String deleteAllSTMT = "TRUNCATE TABLE workspaces, workspaces_members";

    public WorkspaceRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Workspace findByID(UUID id) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(findByIDSTMT)) {
            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                WorkspaceMapper workspaceMapper = new WorkspaceMapper();
                return workspaceMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("WorkspaceRepository::findByID failed");
        }
        throw new IllegalStateException("Workspace with ID: " + id.toString() + "doesn't exist");
    }

    @Override
    public List<Workspace> findAll() {
        List<Workspace> result = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(findAllByIDSTMT)) {
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
    public Workspace create(Workspace entity) {

        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(saveSTMT)) {
            stmt.setObject(1, entity.getId());
            stmt.setString(2, entity.getCreatedBy());
            stmt.setTimestamp(3, Timestamp.valueOf(entity.getCreatedDate()));
            stmt.setString(4, entity.getName());
            stmt.setString(5, entity.getDescription());
            stmt.setString(6, entity.getVisibility().toString());
            addMemberRelations(entity);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Workspace creation failed", e);
        }
        return entity;
    }


    @Override
    public Workspace update(Workspace entity) {
        Workspace updatedWorkspace = null;
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(updateSTMT)) {
            stmt.setTimestamp(1, Timestamp.valueOf(entity.getUpdatedDate()));
            stmt.setString(2, entity.getName());
            stmt.setString(3, entity.getDescription());
            stmt.setObject(4, entity.getVisibility());
            stmt.setObject(5, entity.getId());
            if (!entity.getMembers().isEmpty())
                addMemberRelations(entity);
            stmt.executeUpdate();
            updatedWorkspace = findByID(entity.getId());
        } catch (SQLException e) {
            throw new IllegalStateException("Workspace updating failed", e);
        }
        return updatedWorkspace;
    }

    private void addMemberRelations(Workspace workspace) throws SQLException {

        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement("INSERT INTO workspaces_members(workspace_id, member_id) VALUES (?,?)")) {

            for (Member member : workspace.getMembers()) {
                stmt.setObject(1, workspace.getId());
                stmt.setObject(2, member.getId());
                stmt.executeUpdate();
            }
        }
    }

    private List<Member> getWorkspaceMembers(UUID workspaceId) throws SQLException {
        List<Member> members = new ArrayList<>();
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement("select m.id as id, m.created_by as created_by, m.updated_by as updated_by, m.created_date as created_date, m.role as role, m.user_id as user_id " +
                "from workspaces w join workspaces_members wm on w.id = wm.workspace_id join members m on m.id=wm.member_id where w.id = ?")) {
            stmt.setObject(1, workspaceId);

            if (stmt.execute()) {
                ResultSet resultSet = stmt.getResultSet();
                MemberMapper memberMapper = new MemberMapper();
                while (resultSet.next())
                    members.add(memberMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("WorkspaceRepository::getCardMembers failed");

        }
        return members;
    }

    @Override
    public boolean deleteByID(UUID id) {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(deleteByIDSTMT)) {
            deleteRelations(id);
            stmt.setObject(1, id);
            stmt.executeQuery();

        } catch (SQLException e) {
            throw new IllegalStateException("WorkspaceRepository::deleteByID failed");
        }
        return true;
    }

    @Override
    public boolean deleteAll() {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(deleteAllSTMT)) {
            stmt.executeQuery();

        } catch (SQLException e) {
            throw new IllegalStateException("WorkspaceRepository::deleteAll failed");
        }
        return true;
    }

    private void deleteRelations(UUID id) throws SQLException {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement("DELETE FROM workspaces_members WHERE workspace_id = ?")) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }
    }

}
