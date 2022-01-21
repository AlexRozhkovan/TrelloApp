package spd.trello.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import spd.trello.domain.Board;
import spd.trello.domain.Card;
import spd.trello.domain.CardList;
import spd.trello.domain.Workspace;
import spd.trello.domain.enumerations.BoardVisibility;
import spd.trello.domain.enumerations.WorkspaceVisibility;
import spd.trello.repository.BoardRepository;
import spd.trello.repository.CardListRepository;
import spd.trello.repository.CardRepository;
import spd.trello.repository.WorkspaceRepository;

import static org.junit.jupiter.api.Assertions.*;

class CardServiceTest extends BaseTest {
    private final CardService service = new CardService(new CardRepository(dataSource));
    private final WorkspaceService workspaceService = new WorkspaceService(new WorkspaceRepository(dataSource));
    private final BoardService boardService = new BoardService(new BoardRepository(dataSource));
    private final CardListService cardListService = new CardListService(new CardListRepository(dataSource));


    Workspace workspace = workspaceService.create("Wuser", "WTestName", "WTestDesc", WorkspaceVisibility.PUBLIC);
    Board board = boardService.create("BSuser", "BTestName", "BTestDesc", BoardVisibility.PUBLIC, workspace.getId());
    CardList cardList = cardListService.create("CLuser", "CLTestname", board.getId());
    Card card = service.create("user", "TestName", "TestDesc", cardList.getId());
    Card card1 = service.create("user1", "TestName1", "TestDesc1", cardList.getId());

    @Test
    void create() {
        Assertions.assertNotNull(card);
        Assertions.assertAll(
                () -> Assertions.assertEquals("user", card.getCreatedBy()),
                () -> Assertions.assertEquals("TestName", card.getName()),
                () -> Assertions.assertEquals("TestDesc", card.getDescription())
        );
    }

    @Test
    void update() {
        Card updatedCard = service.update( card.getId(),"updateUser", "UpdateName", "UpdatedDesc", Boolean.TRUE );
        Assertions.assertAll(
                () -> Assertions.assertEquals("UpdateName", updatedCard.getName()),
                () -> Assertions.assertEquals("UpdatedDesc", updatedCard.getDescription()),
                () -> Assertions.assertEquals(Boolean.TRUE, updatedCard.getArchived())
        );
    }

    @Test
    void findAll() {
        Assertions.assertAll(
                () -> Assertions.assertTrue(service.findAll().contains(card)),
                () -> Assertions.assertTrue(service.findAll().contains(card1))
        );
    }

    @Test
    void findByID() {
        service.findByID(card.getId());
        Assertions.assertAll(
                () -> Assertions.assertEquals("user", card.getCreatedBy()),
                () -> Assertions.assertEquals("TestName", card.getName())
        );
    }

    @Test
    void deleteByID() {
        Assertions.assertEquals(service.findByID(card.getId()), card);
        service.deleteByID(card.getId());
        Assertions.assertAll(
                () -> Assertions.assertTrue(service.deleteByID(card.getId())),
                () -> Assertions.assertThrows(IllegalStateException.class, ()->service.findByID(card.getId()))
        );
    }

    @Test
    void deleteAll() {
        service.deleteAll();
        Assertions.assertAll(
                () -> Assertions.assertFalse(service.findAll().contains(card)),
                () -> Assertions.assertFalse(service.findAll().contains(card1))
        );
    }
}