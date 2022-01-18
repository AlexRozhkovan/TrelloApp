package spd.trello.repository;

import spd.trello.domain.Board;
import spd.trello.domain.Member;
import spd.trello.repository.mapper.BoardMapper;
import spd.trello.repository.mapper.MemberMapper;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BoardRepository implements IRepository<Board> {

    private static DataSource dataSource;
    private static final String saveSTMT = "insert into boards(id, created_by, created_date, name, description, archived, visibility, workspace_id) values (?,?,?,?,?,?,?,?)";
    private static final String findByIDSTMT = "SELECT * FROM boards WHERE id=?";
    private static final String findAllByIDSTMT = "SELECT * FROM boards";
    private static final String updateSTMT = "UPDATE boards SET updated_by=?,updated_date=?, name=?, description=?, archived=?, visibility=? WHERE id =?";
    private static final String deleteByIDSTMT = "DELETE FROM boards WHERE id=? ";
    private static final String deleteAllSTMT = "DELETE FROM boards";

    public BoardRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Board findById(UUID id) {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(findByIDSTMT)) {
            stmt.setObject(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                BoardMapper boardMapper = new BoardMapper();
                return boardMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("BoardRepository::findByID failed");
        }
        throw new IllegalStateException("Board with ID: " + id.toString() + "doesn't exist");
    }

    @Override
    public List<Board> findAll() {
        List<Board> result = new ArrayList<>();
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(findAllByIDSTMT)) {
            ResultSet resultSet = stmt.executeQuery();
            BoardMapper boardMapper = new BoardMapper();
            while (resultSet.next()) {
                result.add(boardMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("BoardRepository::findALL failed");
        }
        return result;
    }

    @Override
    public Board create(Board entity) {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(saveSTMT)) {
            stmt.setObject(1, entity.getId());
            stmt.setString(2, entity.getCreatedBy());
            stmt.setTimestamp(3, Timestamp.valueOf(entity.getCreatedDate()));
            stmt.setString(4, entity.getName());
            stmt.setString(5, entity.getDescription());
            stmt.setBoolean(6, entity.getArchived());
            stmt.setObject(7, entity.getVisibility().toString());
            stmt.setObject(8, entity.getWorkspaceId());
            addMemberRelations(entity);
            stmt.executeUpdate();
            return findById(entity.getId());
        } catch (SQLException e) {
            throw new IllegalStateException("Board creation failed", e);
        }
    }

    @Override
    public Board update(Board entity) {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(updateSTMT)) {
            stmt.setString(1, entity.getCreatedBy());
            stmt.setTimestamp(2, Timestamp.valueOf(entity.getUpdatedDate()));
            stmt.setString(3, entity.getName());
            stmt.setString(4, entity.getDescription());
            stmt.setBoolean(5, entity.getArchived());
            stmt.setObject(6, entity.getVisibility().toString());
            stmt.setObject(7, entity.getId());
            if (!entity.getMembers().isEmpty())
                addMemberRelations(entity);
            stmt.executeUpdate();
            return findById(entity.getId());
        } catch (SQLException e) {
            throw new IllegalStateException("Board updating failed", e);
        }
    }

    private void addMemberRelations(Board board) throws SQLException {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement("INSERT INTO boards_members(board_id, member_id) VALUES (?,?) ON CONFLICT DO NOTHING ")) {
            for (Member member : board.getMembers()) {
                stmt.setObject(1, board.getId());
                stmt.setObject(2, member.getId());
                stmt.executeUpdate();
            }
        }
    }

    private List<Member> getBoardMembers(UUID boardId) {
        List<Member> members = new ArrayList<>();
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement("select m.id as id, m.created_by as created_by, m.updated_by as updated_by, m.created_date as created_date, m.role as role, m.user_id as user_id " +
                "from boards b join boards_members bm on b.id = bm.board_id join members m on m.id=bm.member_id where b.id = ?")) {
            stmt.setObject(1, boardId);
            if (stmt.execute()) {
                ResultSet resultSet = stmt.getResultSet();
                MemberMapper memberMapper = new MemberMapper();
                while (resultSet.next())
                    members.add(memberMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("BoardRepository::getBoardMembers failed");
        }
        return members;
    }

    @Override
    public boolean deleteByID(UUID id) {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(deleteByIDSTMT)) {
            deleteRelation(id);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("BoardRepository::deleteByID failed");
        }
        return true;
    }

    @Override
    public boolean deleteAll() {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(deleteAllSTMT)) {
            deleteRelations();
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("BoardRepository::deleteAll failed");
        }
        return true;
    }

    private void deleteRelation(UUID id) throws SQLException {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement("DELETE FROM boards_members WHERE board_id = ?")) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }
    }

    private void deleteRelations() throws SQLException {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement("DELETE FROM boards_members")) {
            stmt.executeUpdate();
        }
    }
}
