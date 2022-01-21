package spd.trello.repository;

import spd.trello.domain.Member;
import spd.trello.repository.mapper.MemberMapper;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MemberRepository implements IRepository<Member> {

    private DataSource dataSource;
    private final String saveSTMT = "insert into members(id, created_by, created_date, role, user_id) values (?,?,?,?,?)";
    private final String findByIDSTMT = "SELECT * FROM members WHERE id=?";
    private final String findAllByIDSTMT = "SELECT * FROM members";
    private final String updateSTMT = "UPDATE members SET updated_by=?, updated_date=?, role=? WHERE id =?";
    private final String deleteByIDSTMT = "DELETE FROM members WHERE id=? ";
    private final String deleteAllSTMT = "DELETE FROM members";

    public MemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Member findById(UUID id) {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(findByIDSTMT)) {
            stmt.setObject(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                MemberMapper memberMapper = new MemberMapper();
                return memberMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("MemberRepository::findByID failed");
        }
        throw new IllegalStateException("Member with ID: " + id.toString() + "doesn't exist");
    }

    @Override
    public List<Member> findAll() {
        List<Member> result = new ArrayList<>();
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(findAllByIDSTMT)) {
            ResultSet resultSet;
            resultSet = stmt.executeQuery();
            MemberMapper memberMapper = new MemberMapper();
            while (resultSet.next()) {
                result.add(memberMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("MemberRepository::findALL failed");
        }
        return result;
    }

    @Override
    public Member create(Member entity) {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(saveSTMT)) {
            stmt.setObject(1, entity.getId());
            stmt.setString(2, entity.getCreatedBy());
            stmt.setTimestamp(3, Timestamp.valueOf(entity.getCreatedDate()));
            stmt.setObject(4, entity.getRole().toString());
            stmt.setObject(5, entity.getUser());
            stmt.executeUpdate();
            return findById(entity.getId());
        } catch (SQLException e) {
            throw new IllegalStateException("Member creation failed", e);
        }
    }

    @Override
    public Member update(Member entity) {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(updateSTMT)) {
            stmt.setString(1, entity.getUpdatedBy());
            stmt.setTimestamp(2, Timestamp.valueOf(entity.getUpdatedDate()));
            stmt.setObject(3, entity.getRole().toString());
            stmt.setObject(4, entity.getId());
            stmt.executeUpdate();
            return findById(entity.getId());
        } catch (SQLException e) {
            throw new IllegalStateException("Member updating failed", e);
        }
    }

    @Override
    public boolean deleteByID(UUID id) {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(deleteByIDSTMT)) {
            deleteRelation(id);
            stmt.setObject(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("MemberRepository::deleteByID failed");
        }
        return true;
    }

    @Override
    public boolean deleteAll() {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(deleteAllSTMT)) {
            deleteRelations();
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("MemberRepository::deleteAll failed");
        }
        return true;
    }

    private void deleteRelation(UUID id) throws SQLException {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement("DELETE FROM cards_members WHERE card_id = ?")
        ) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }
    }

    private void deleteRelations() throws SQLException {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement("DELETE FROM cards_members")
        ) {
            stmt.executeUpdate();
        }
    }
}
