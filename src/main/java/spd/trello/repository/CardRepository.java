package spd.trello.repository;

import spd.trello.domain.Card;
import spd.trello.domain.Member;
import spd.trello.repository.mapper.CardMapper;
import spd.trello.repository.mapper.MemberMapper;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CardRepository implements IRepository<Card> {

    private final DataSource dataSource;
    private final String saveSTMT = "insert into cards(id, created_by, created_date, name, description, archived, cardList_id) values (?,?,?,?,?,?,?)";
    private final String findByIDSTMT = "SELECT * FROM cards WHERE id=?";
    private final String findAllByIDSTMT = "SELECT * FROM cards";
    private final String updateSTMT = "UPDATE cards SET updated_by=?,updated_date=?, name=?, description=?, archived=?, cardlist_id=? WHERE id =?";
    private final String deleteByIDSTMT = "DELETE FROM cards WHERE id=? ";
    private final String deleteAllSTMT = "DELETE FROM cards";

    public CardRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Card findById(UUID id) {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(findByIDSTMT)) {
            stmt.setObject(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                CardMapper cardMapper = new CardMapper();
                return cardMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("CardRepository::findByID failed");
        }
        throw new IllegalStateException("Card with ID: " + id.toString() + "doesn't exist");
    }

    @Override
    public List<Card> findAll() {
        List<Card> result = new ArrayList<>();
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(findAllByIDSTMT)) {
            ResultSet resultSet;
            resultSet = stmt.executeQuery();
            CardMapper cardMapper = new CardMapper();
            while (resultSet.next()) {
                result.add(cardMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("CardRepository::findALL failed");
        }
        return result;
    }

    @Override
    public Card create(Card entity) {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(saveSTMT)) {
            stmt.setObject(1, entity.getId());
            stmt.setString(2, entity.getCreatedBy());
            stmt.setTimestamp(3, Timestamp.valueOf(entity.getCreatedDate()));
            stmt.setString(4, entity.getName());
            stmt.setString(5, entity.getDescription());
            stmt.setBoolean(6, entity.getArchived());
            stmt.setObject(7, entity.getCardListId());
            addMemberRelations(entity);
            stmt.executeUpdate();
            return findById(entity.getId());
        } catch (SQLException e) {
            throw new IllegalStateException("Card creation failed", e);
        }
    }

    @Override
    public Card update(Card entity) {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(updateSTMT)) {
            stmt.setString(1, entity.getCreatedBy());
            stmt.setTimestamp(2, Timestamp.valueOf(entity.getUpdatedDate()));
            stmt.setString(3, entity.getName());
            stmt.setString(4, entity.getDescription());
            stmt.setBoolean(5, entity.getArchived());
            stmt.setObject(6, entity.getCardListId());
            stmt.setObject(7, entity.getId());
            if (!entity.getAssignedMembers().isEmpty())
                addMemberRelations(entity);
            stmt.executeUpdate();
            return findById(entity.getId());
        } catch (SQLException e) {
            throw new IllegalStateException("Card updating failed", e);
        }
    }

    private void addMemberRelations(Card card) throws SQLException {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement("INSERT INTO cards_members(card_id, member_id) VALUES (?,?) ON CONFLICT DO NOTHING ")) {
            for (Member member : card.getAssignedMembers()) {
                stmt.setObject(1, card.getId());
                stmt.setObject(2, member.getId());
                stmt.executeUpdate();
            }
        }
    }

    private List<Member> getCardMembers(UUID cardId) throws SQLException {
        List<Member> members = new ArrayList<>();
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement("select m.id as id, m.created_by as created_by, m.updated_by as updated_by, m.created_date as created_date, m.role as role, m.user_id as user_id " +
                "from cards c join cards_members cm on c.id = cm.card_id join members m on m.id=cm.member_id where c.id = ?")) {
            stmt.setObject(1, cardId);
            if (stmt.execute()) {
                ResultSet resultSet = stmt.getResultSet();
                MemberMapper memberMapper = new MemberMapper();
                while (resultSet.next())
                    members.add(memberMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("CardRepository::getCardMembers failed");
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
            throw new IllegalStateException("CardRepository::deleteByID failed");
        }
        return true;
    }

    @Override
    public boolean deleteAll() {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(deleteAllSTMT)) {
            deleteRelations();
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("CardRepository::deleteAll failed");
        }
        return true;
    }

    private void deleteRelation(UUID id) throws SQLException {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement("DELETE FROM cards_members WHERE card_id = ?")) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
        }
    }

    private void deleteRelations() throws SQLException {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement("DELETE FROM cards_members")) {
            stmt.executeUpdate();
        }
    }

}
