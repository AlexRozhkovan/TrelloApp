/*
package spd.trello.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spd.trello.domain.Board;
import spd.trello.domain.Card;
import spd.trello.domain.CardList;
import spd.trello.domain.Workspace;
import spd.trello.domain.enumerations.BoardVisibility;
import spd.trello.domain.enumerations.WorkspaceVisibility;

@SpringBootTest
class CardServiceTest extends BaseTest {
    @Autowired
    WorkspaceService workspaceService;
    @Autowired
    BoardService boardService;
    @Autowired
    CardListService cardListService;
    @Autowired
    CardService service;

    @Test
    void create() {
        Workspace workspace = workspaceService.create("Wuser", "WTestName", "WTestDesc", WorkspaceVisibility.PUBLIC);
        Board board = boardService.create("BSuser", "BTestName", "BTestDesc", BoardVisibility.PUBLIC, workspace.getId());
        CardList cardList = cardListService.create("CLuser", "CLTestname", board.getId());
        Card card = service.create("user", "TestName", "TestDesc", cardList.getId());
        Assertions.assertNotNull(card);
        Assertions.assertAll(
                () -> Assertions.assertEquals("user", card.getCreatedBy()),
                () -> Assertions.assertEquals("TestName", card.getName()),
                () -> Assertions.assertEquals("TestDesc", card.getDescription())
        );
    }

    @Test
    void update() {
        Workspace workspace = workspaceService.create("Wuser", "WTestName", "WTestDesc", WorkspaceVisibility.PUBLIC);
        Board board = boardService.create("BSuser", "BTestName", "BTestDesc", BoardVisibility.PUBLIC, workspace.getId());
        CardList cardList = cardListService.create("CLuser", "CLTestname", board.getId());
        Card card = service.create("user", "TestName", "TestDesc", cardList.getId());
        Card updatedCard = service.update(card.getId(), "updateUser", "UpdateName", "UpdatedDesc", Boolean.TRUE);
        Assertions.assertAll(
                () -> Assertions.assertEquals("UpdateName", updatedCard.getName()),
                () -> Assertions.assertEquals("UpdatedDesc", updatedCard.getDescription()),
                () -> Assertions.assertEquals(Boolean.TRUE, updatedCard.getArchived())
        );
    }

    @Test
    void findAll() {
        Workspace workspace = workspaceService.create("Wuser", "WTestName", "WTestDesc", WorkspaceVisibility.PUBLIC);
        Board board = boardService.create("BSuser", "BTestName", "BTestDesc", BoardVisibility.PUBLIC, workspace.getId());
        CardList cardList = cardListService.create("CLuser", "CLTestname", board.getId());
        Card card = service.create("user", "TestName", "TestDesc", cardList.getId());
        Card card1 = service.create("user1", "TestName1", "TestDesc1", cardList.getId());
        Assertions.assertAll(
                () -> Assertions.assertTrue(service.findAll().contains(card)),
                () -> Assertions.assertTrue(service.findAll().contains(card1))
        );
    }

    @Test
    void findByID() {
        Workspace workspace = workspaceService.create("Wuser", "WTestName", "WTestDesc", WorkspaceVisibility.PUBLIC);
        Board board = boardService.create("BSuser", "BTestName", "BTestDesc", BoardVisibility.PUBLIC, workspace.getId());
        CardList cardList = cardListService.create("CLuser", "CLTestname", board.getId());
        Card card = service.create("user", "TestName", "TestDesc", cardList.getId());
        service.findByID(card.getId());
        Assertions.assertAll(
                () -> Assertions.assertEquals("user", card.getCreatedBy()),
                () -> Assertions.assertEquals("TestName", card.getName())
        );
    }

    @Test
    void deleteByID() {
        Workspace workspace = workspaceService.create("Wuser", "WTestName", "WTestDesc", WorkspaceVisibility.PUBLIC);
        Board board = boardService.create("BSuser", "BTestName", "BTestDesc", BoardVisibility.PUBLIC, workspace.getId());
        CardList cardList = cardListService.create("CLuser", "CLTestname", board.getId());
        Card card = service.create("user", "TestName", "TestDesc", cardList.getId());
        Assertions.assertTrue(service.deleteByID(card.getId()));

    }
}
*/
