package spd.trello.repository.mapper;

import spd.trello.domain.Board;
import spd.trello.domain.enumerations.BoardVisibility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class BoardMapper {
    public Board extractFromResultSet(ResultSet resultSet) throws SQLException {
        Board board = new Board();
        board.setId(UUID.fromString(resultSet.getString("id")));
        board.setCreatedBy(resultSet.getString("created_by"));
        board.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
        board.setName(resultSet.getString("name"));
        board.setDescription(resultSet.getString("description"));
        board.setArchived(resultSet.getBoolean("archived"));
        board.setVisibility((BoardVisibility.valueOf( resultSet.getString("visibility"))));
        board.setWorkspaceId(UUID.fromString(resultSet.getString("workspace_id")));
        return board;
    }
}