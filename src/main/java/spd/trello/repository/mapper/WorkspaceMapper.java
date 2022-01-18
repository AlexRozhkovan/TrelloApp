package spd.trello.repository.mapper;

import spd.trello.domain.Workspace;
import spd.trello.domain.enumerations.WorkspaceVisibility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class WorkspaceMapper {
    public Workspace extractFromResultSet(ResultSet resultSet) throws SQLException {
        Workspace workspace = new Workspace();
        workspace.setId(UUID.fromString(resultSet.getString("id")));
        workspace.setCreatedBy(resultSet.getString("created_by"));
        workspace.setName(resultSet.getString("name"));
        workspace.setDescription(resultSet.getString("description"));
        workspace.setVisibility((WorkspaceVisibility.valueOf(resultSet.getString("visibility"))));
        return workspace;
    }
}
