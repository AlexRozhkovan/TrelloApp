/*
package spd.trello.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spd.trello.domain.Board;
import spd.trello.domain.Workspace;
import spd.trello.domain.enumerations.BoardVisibility;
import spd.trello.domain.enumerations.WorkspaceVisibility;

@SpringBootTest
class BoardServiceTest extends BaseTest {

    @Autowired
    WorkspaceService workspaceService;
    @Autowired
    BoardService service;

    @Test
    void create() {
        Workspace workspace = workspaceService.create("WSuser", "WSTestname", "WSTestDesc", WorkspaceVisibility.PUBLIC);
        Board board = service.create("user", "TestName", "TestDesc", BoardVisibility.PRIVATE, workspace.getId());
        Assertions.assertNotNull(board);
        Assertions.assertAll(
                () -> Assertions.assertEquals("user", board.getCreatedBy()),
                () -> Assertions.assertEquals("TestName", board.getName()),
                () -> Assertions.assertEquals("TestDesc", board.getDescription()),
                () -> Assertions.assertEquals(BoardVisibility.PRIVATE, board.getVisibility())
        );
    }

    @Test
    void update() {
        Workspace workspace = workspaceService.create("WSuser", "WSTestname", "WSTestDesc", WorkspaceVisibility.PUBLIC);
        Board board = service.create("user", "TestName", "TestDesc", BoardVisibility.PRIVATE, workspace.getId());
        Board updatedBoard = service.update(board.getId(), "updateUser", "UpdateName", "UpdateDesc", Boolean.TRUE, BoardVisibility.PUBLIC);
        Assertions.assertAll(
                () -> Assertions.assertEquals("UpdateName", updatedBoard.getName()),
                () -> Assertions.assertEquals("UpdateDesc", updatedBoard.getDescription()),
                () -> Assertions.assertEquals(Boolean.TRUE, updatedBoard.getArchived()),
                () -> Assertions.assertEquals(BoardVisibility.PUBLIC, updatedBoard.getVisibility())
        );
    }

    @Test
    void findAll() {
        Workspace workspace = workspaceService.create("WSuser", "WSTestname", "WSTestDesc", WorkspaceVisibility.PUBLIC);
        Board board = service.create("user", "TestName", "TestDesc", BoardVisibility.PRIVATE, workspace.getId());
        Board board1 = service.create("user1", "TestName", "TestDesc", BoardVisibility.PRIVATE, workspace.getId());
        Assertions.assertAll(
                () -> Assertions.assertTrue(service.findAll().contains(board)),
                () -> Assertions.assertTrue(service.findAll().contains(board1))
        );
    }

    @Test
    void findByID() {
        Workspace workspace = workspaceService.create("WSuser", "WSTestname", "WSTestDesc", WorkspaceVisibility.PUBLIC);
        Board board = service.create("user", "TestName", "TestDesc", BoardVisibility.PRIVATE, workspace.getId());
        service.findByID(board.getId());
        Assertions.assertAll(
                () -> Assertions.assertEquals("user", board.getCreatedBy()),
                () -> Assertions.assertEquals("TestName", board.getName()),
                () -> Assertions.assertEquals("TestDesc", board.getDescription()),
                () -> Assertions.assertEquals(BoardVisibility.PRIVATE, board.getVisibility())
        );
    }

    @Test
    void deleteByID() {
        Workspace workspace = workspaceService.create("WSuser", "WSTestname", "WSTestDesc", WorkspaceVisibility.PUBLIC);
        Board board = service.create("user", "TestName", "TestDesc", BoardVisibility.PRIVATE, workspace.getId());

        Assertions.assertTrue(service.deleteByID(board.getId()));
    }

}
*/
