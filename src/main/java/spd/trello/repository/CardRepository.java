package spd.trello.repository;

import spd.trello.domain.Card;
import spd.trello.repository.mapper.CardMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CardRepository implements IRepository<Card>{

    private final DataSource dataSource;
    private final String saveSTMT = "insert into cards(id, created_date, name, description, archived, cardList_id) values (?,?,?,?,?)";
    private final String findByIDSTMT = "SELECT * FROM cards WHERE id=?";
    private final String findAllByIDSTMT = "SELECT * FROM cards";

    public CardRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public Card findByID(UUID id){
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(findByIDSTMT)){
            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                CardMapper cardMapper = new CardMapper();
                return cardMapper.extractFromResultSet(resultSet);
            }
        }catch (SQLException e){
            throw new IllegalStateException("CardRepository::findByID failed");
        }
        throw new IllegalStateException("Card with ID: " + id.toString() + "doesn't exist");
    }

    public List<Card> findAll() {
        List<Card> result = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement statement = con.prepareStatement(findAllByIDSTMT)){
            ResultSet resultSet;
            resultSet = statement.executeQuery();
            CardMapper cardMapper = new CardMapper();
            while (resultSet.next()) {
                result.add(cardMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    @Override
    public void create(Card entity) {

        try (Connection con = dataSource.getConnection();
             PreparedStatement stmt = con.prepareStatement(saveSTMT)) {
            stmt.setObject(1, entity.getId());
            stmt.setTimestamp(2, Timestamp.valueOf(entity.getCreatedDate()));
            stmt.setString(3, entity.getName());
            stmt.setString(4, entity.getDescription());
            stmt.setBoolean(5, entity.getArchived());
            //stmt.setObject(6, UUID.fromString("77051dfc-9947-4ad3-9336-da72f54abaea")); //TODO

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Card creation failed", e);
        }
    }

 /* @Override
    public Card update(Card entity) {
        throw new NotImplementedException("DemoUserRepository::update not implemented");
    }

    @Override
    public boolean delete(UUID id) {
        throw new NotImplementedException("DemoUserRepository::delete not implemented");
    }
*/



}
