package spd.trello.repository.mapper;

import spd.trello.domain.Card;
import spd.trello.domain.CardList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CardListMapper {
    public CardList extractFromResultSet(ResultSet resultSet) throws SQLException {
        CardList cardList = new CardList();
        cardList.setId(UUID.fromString(resultSet.getString("id")));
        cardList.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
        cardList.setCreatedBy(resultSet.getString("created_by"));
        cardList.setName(resultSet.getString("name"));
        cardList.setArchived(resultSet.getBoolean("archived"));
        cardList.setBoardId(UUID.fromString(resultSet.getString("board_id")));
        return cardList;
    }
}
