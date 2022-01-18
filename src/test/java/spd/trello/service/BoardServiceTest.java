package spd.trello.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import spd.trello.domain.Board;
import spd.trello.domain.Workspace;
import spd.trello.domain.enumerations.BoardVisibility;
import spd.trello.domain.enumerations.WorkspaceVisibility;
import spd.trello.repository.BoardRepository;
import spd.trello.repository.WorkspaceRepository;


class BoardServiceTest extends BaseTest {
    private final BoardService service = new BoardService(new BoardRepository(dataSource));
    private final WorkspaceService workspaceService = new WorkspaceService(new WorkspaceRepository(dataSource));
    ;

    Workspace workspace = workspaceService.create("WSuser", "WSTestname", "WSTestDesc", WorkspaceVisibility.PUBLIC);
    Board board = service.create("user", "TestName", "TestDesc", BoardVisibility.PRIVATE, workspace.getId());
    Board board1 = service.create("user1", "TestName", "TestDesc", BoardVisibility.PRIVATE, workspace.getId());

    @Test
    void create() {
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
        Assertions.assertAll(
                () -> Assertions.assertTrue(service.findAll().contains(board)),
                () -> Assertions.assertTrue(service.findAll().contains(board1))
        );
    }

    @Test
    void findByID() {
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
        Assertions.assertEquals(service.findByID(board.getId()), board);
        service.deleteByID(board.getId());
        Assertions.assertAll(
                () -> Assertions.assertTrue(service.deleteByID(board.getId())),
                () -> Assertions.assertThrows(IllegalStateException.class, () -> service.findByID(board.getId()))
        );
    }

    @Test
    void deleteAll() {
        service.deleteAll();
        Assertions.assertAll(
                () -> Assertions.assertFalse(service.findAll().contains(board)),
                () -> Assertions.assertFalse(service.findAll().contains(board1))
        );
    }
}