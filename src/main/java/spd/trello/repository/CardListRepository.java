package spd.trello.repository;

import spd.trello.domain.CardList;
import spd.trello.repository.mapper.CardListMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CardListRepository implements IRepository<CardList> {

    private final DataSource dataSource;
    private final String saveSTMT = "insert into cardLists(id, created_date, name, archived, board_id) values (?,?,?,?,?)";
    private final String findByIDSTMT = "SELECT * FROM cardLists WHERE id=?";
    private final String findAllByIDSTMT = "SELECT * FROM cardLists";
    private final String updateSTMT = "UPDATE cardLists SET updated_date =?, name =?, archived=?, board_id=? WHERE id =?";
    private final String deleteByIDSTMT = "DELETE FROM cardLists WHERE id=? ";
    private final String deleteAllSTMT = "TRUNCATE TABLE cardLists";

    public CardListRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public CardList findByID(UUID id) {
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(findByIDSTMT)) {
            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                CardListMapper cardListMapper = new CardListMapper();
                return cardListMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("CardListRepository::findByID failed");
        }
        throw new IllegalStateException("CardList with ID: " + id.toString() + "doesn't exist");
    }

    public List<CardList> findAll() {
        List<CardList> result = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(findAllByIDSTMT)) {
            ResultSet resultSet;
            resultSet = statement.executeQuery();
            CardListMapper cardListMapper = new CardListMapper();
            while (resultSet.next()) {
                result.add(cardListMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public CardList create(CardList entity) {

        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement(saveSTMT)) {
            stmt.setObject(1, entity.getId());
            stmt.setTimestamp(2, Timestamp.valueOf(entity.getCreatedDate()));
            stmt.setString(3, entity.getName());
            stmt.setBoolean(4, entity.getArchived());
            //stmt.setObject(6, UUID.fromString("77051dfc-9947-4ad3-9336-da72f54abaea")); //TODO

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("CardList creation failed", e);
        }
        return null;
    }

    @Override
    public CardList update(CardList entity) {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(updateSTMT)) {
            stmt.setTimestamp(1, Timestamp.valueOf(entity.getUpdatedDate()));
            stmt.setString(2, entity.getName());
            stmt.setBoolean(3, entity.getArchived());
            stmt.setObject(4, entity.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("CardList updating failed", e);
        }
        return null;
    }

    @Override
    public boolean deleteByID(UUID id) {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(deleteByIDSTMT)) {
            stmt.setObject(1, id);
            stmt.executeQuery();

        } catch (SQLException e) {
            throw new IllegalStateException("CardListRepository::deleteByID failed");
        }
        return true;
    }

    @Override
    public boolean deleteAll() {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(deleteAllSTMT)) {
            stmt.executeQuery();

        } catch (SQLException e) {
            throw new IllegalStateException("CardListRepository::deleteAll failed");
        }
        return true;

    }
}
