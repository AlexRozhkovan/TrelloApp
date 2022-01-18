package spd.trello.repository.mapper;

import spd.trello.domain.Card;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CardMapper {
    public Card extractFromResultSet(ResultSet resultSet) throws SQLException {
        Card card = new Card();
        card.setId(UUID.fromString(resultSet.getString("id")));
        card.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
        card.setCreatedBy(resultSet.getString("created_by"));
        card.setName(resultSet.getString("name"));
        card.setDescription(resultSet.getString("description"));
        card.setArchived(resultSet.getBoolean("archived"));
        card.setCardListId(UUID.fromString(resultSet.getString("cardList_id")));
        return card;
    }
}
