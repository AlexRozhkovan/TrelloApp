package spd.trello.repository;

import spd.trello.domain.Board;
import spd.trello.domain.Card;
import spd.trello.repository.mapper.BoardMapper;
import spd.trello.repository.mapper.CardMapper;
import spd.trello.repository.mapper.WorkspaceMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BoardRepository  implements IRepository<Board>{

    private final DataSource dataSource;
    private final String saveSTMT = "insert into boards(id, created_date, name, description, archived, visibility, workspace_id) values (?,?,?,?,?,?)";
    private final String findByIDSTMT = "SELECT * FROM boards WHERE id=?";
    private final String findAllByIDSTMT = "SELECT * FROM boards";

    public BoardRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public Board findByID(UUID id){
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(findByIDSTMT)){
            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                BoardMapper boardMapper = new BoardMapper();
                return boardMapper.extractFromResultSet(resultSet);
            }
        }catch (SQLException e){
            throw new IllegalStateException("BoardRepository::findByID failed");
        }
        throw new IllegalStateException("Board with ID: " + id.toString() + "doesn't exist");
    }

    public List<Board> findAll() {
        List<Board> result = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(findAllByIDSTMT)){
            ResultSet resultSet;
            resultSet = statement.executeQuery();
            BoardMapper boardMapper = new BoardMapper();
            while (resultSet.next()) {
                result.add(boardMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public void create(Board entity) {

        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement(saveSTMT)) {
            stmt.setObject(1, entity.getId());
            stmt.setTimestamp(2, Timestamp.valueOf(entity.getCreatedDate()));
            stmt.setString(3, entity.getName());
            stmt.setString(4, entity.getDescription());
            stmt.setBoolean(5, entity.getArchived());
            stmt.setString(6, entity.getVisibility().toString());
            //stmt.setObject(6, UUID.fromString("77051dfc-9947-4ad3-9336-da72f54abaea")); //TODO

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Board creation failed", e);
        }
    }
}
