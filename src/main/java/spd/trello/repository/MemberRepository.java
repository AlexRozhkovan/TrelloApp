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
    private final String updateSTMT = "UPDATE members SET updated_by=?, updated_date=?, role=?, user_id=? WHERE id =?";
    private final String deleteByIDSTMT = "DELETE FROM members WHERE id=? ";
    private final String deleteAllSTMT = "TRUNCATE TABLE members, workspaces_members, cards_members";

    public MemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public MemberRepository() {

    }

    @Override
    public Member findByID(UUID id) {
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
            stmt.setString(4, String.valueOf(entity.getRole()));
            stmt.setObject(5, entity.getUser().getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("User creation failed", e);
        }
        return entity;
    }

    @Override
    public Member update(Member entity) {
        Member updatedMember = null;
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(updateSTMT)) {
            stmt.setString(1, entity.getUpdatedBy());
            stmt.setTimestamp(2, Timestamp.valueOf(entity.getUpdatedDate()));
            stmt.setString(3, String.valueOf(entity.getRole()));
            stmt.setObject(4, entity.getUser().getId());
            stmt.setObject(5, entity.getId());
            stmt.executeUpdate();
            updatedMember = findByID(entity.getId());
        } catch (SQLException e) {
            throw new IllegalStateException("User updating failed", e);
        }
        return updatedMember;
    }

    @Override
    public boolean deleteByID(UUID id) {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(deleteByIDSTMT)) {
            deleteRelations(id);
            stmt.setObject(1, id);
            stmt.executeQuery();

        } catch (SQLException e) {
            throw new IllegalStateException("MemberRepository::deleteByID failed");
        }
        return true;
    }

    private void deleteRelations(UUID id) throws SQLException {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement("DELETE FROM cards_members WHERE card_id = ?")) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public boolean deleteAll() {

        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(deleteAllSTMT)) {
            stmt.executeQuery();

        } catch (SQLException e) {
            throw new IllegalStateException("MemberRepository::deleteAll failed");
        }
        return true;
    }
}
