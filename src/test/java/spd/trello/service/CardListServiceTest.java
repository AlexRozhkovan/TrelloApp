/*
package spd.trello.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spd.trello.domain.Board;
import spd.trello.domain.CardList;
import spd.trello.domain.Workspace;
import spd.trello.domain.enumerations.BoardVisibility;
import spd.trello.domain.enumerations.WorkspaceVisibility;

@SpringBootTest
class CardListServiceTest extends BaseTest {

    @Autowired
    WorkspaceService workspaceService;
    @Autowired
    BoardService boardService;
    @Autowired
    CardListService service;

    @Test
    void create() {
        Workspace workspace = workspaceService.create("Wuser", "WTestName", "WTestDesc", WorkspaceVisibility.PUBLIC);
        Board board = boardService.create("BSuser", "BTestName", "BTestDesc", BoardVisibility.PUBLIC, workspace.getId());
        CardList cardList = service.create("user", "TestName", board.getId());
        Assertions.assertNotNull(cardList);
        Assertions.assertAll(
                () -> Assertions.assertEquals("user", cardList.getCreatedBy()),
                () -> Assertions.assertEquals("TestName", cardList.getName())
        );
    }

    @Test
    void update() {
        Workspace workspace = workspaceService.create("Wuser", "WTestName", "WTestDesc", WorkspaceVisibility.PUBLIC);
        Board board = boardService.create("BSuser", "BTestName", "BTestDesc", BoardVisibility.PUBLIC, workspace.getId());
        CardList cardList = service.create("user", "TestName", board.getId());
        CardList updatedCardList = service.update(cardList.getId(), "updateUser", "UpdateName");
        Assertions.assertEquals("UpdateName", updatedCardList.getName());
    }

    @Test
    void findAll() {
        Workspace workspace = workspaceService.create("Wuser", "WTestName", "WTestDesc", WorkspaceVisibility.PUBLIC);
        Board board = boardService.create("BSuser", "BTestName", "BTestDesc", BoardVisibility.PUBLIC, workspace.getId());
        CardList cardList = service.create("user", "TestName", board.getId());
        CardList cardList1 = service.create("user1", "TestName1", board.getId());
        Assertions.assertAll(
                () -> Assertions.assertTrue(service.findAll().contains(cardList)),
                () -> Assertions.assertTrue(service.findAll().contains(cardList1))
        );
    }

    @Test
    void findByID() {
        Workspace workspace = workspaceService.create("Wuser", "WTestName", "WTestDesc", WorkspaceVisibility.PUBLIC);
        Board board = boardService.create("BSuser", "BTestName", "BTestDesc", BoardVisibility.PUBLIC, workspace.getId());
        CardList cardList = service.create("user", "TestName", board.getId());
        Assertions.assertAll(
                () -> Assertions.assertEquals("user", cardList.getCreatedBy()),
                () -> Assertions.assertEquals("TestName", cardList.getName())
        );
    }

    @Test
    void deleteByID() {
        Workspace workspace = workspaceService.create("Wuser", "WTestName", "WTestDesc", WorkspaceVisibility.PUBLIC);
        Board board = boardService.create("BSuser", "BTestName", "BTestDesc", BoardVisibility.PUBLIC, workspace.getId());
        CardList cardList = service.create("user", "TestName", board.getId());
        Assertions.assertTrue(service.deleteByID(cardList.getId()));
    }
}
*/
