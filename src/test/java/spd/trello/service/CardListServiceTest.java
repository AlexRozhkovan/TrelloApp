package spd.trello.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import spd.trello.domain.Board;
import spd.trello.domain.CardList;
import spd.trello.domain.Workspace;
import spd.trello.domain.enumerations.BoardVisibility;
import spd.trello.domain.enumerations.WorkspaceVisibility;
import spd.trello.repository.BoardRepository;
import spd.trello.repository.CardListRepository;
import spd.trello.repository.WorkspaceRepository;

class CardListServiceTest extends BaseTest {
    private final CardListService service = new CardListService(new CardListRepository(dataSource));
    private final BoardService boardService = new BoardService(new BoardRepository(dataSource));
    private final WorkspaceService workspaceService = new WorkspaceService(new WorkspaceRepository(dataSource));

    Workspace workspace = workspaceService.create("Wuser", "WTestName", "WTestDesc", WorkspaceVisibility.PUBLIC);
    Board board = boardService.create("BSuser", "BTestName", "BTestDesc", BoardVisibility.PUBLIC, workspace.getId());
    CardList cardList = service.create("user", "TestName", board.getId());
    CardList cardList1 = service.create("user1", "TestName1", board.getId());

    @Test
    void create() {
        Assertions.assertNotNull(cardList);
        Assertions.assertAll(
                () -> Assertions.assertEquals("user", cardList.getCreatedBy()),
                () -> Assertions.assertEquals("TestName", cardList.getName())
        );
    }

    @Test
    void update() {
        CardList updatedCardList = service.update(cardList.getId(), "updateUser", "UpdateName");
        Assertions.assertEquals("UpdateName", updatedCardList.getName());
    }

    @Test
    void findAll() {
        Assertions.assertAll(
                () -> Assertions.assertTrue(service.findAll().contains(cardList)),
                () -> Assertions.assertTrue(service.findAll().contains(cardList1))
        );
    }

    @Test
    void findByID() {
        Assertions.assertAll(
                () -> Assertions.assertEquals("user", cardList.getCreatedBy()),
                () -> Assertions.assertEquals("TestName", cardList.getName())
        );
    }

    @Test
    void deleteByID() {
        Assertions.assertEquals(service.findByID(cardList.getId()), cardList);
        service.deleteByID(cardList.getId());
        Assertions.assertAll(
                () -> Assertions.assertTrue(service.deleteByID(cardList.getId())),
                () -> Assertions.assertThrows(IllegalStateException.class, () -> service.findByID(cardList.getId()))
        );
    }

    @Test
    void deleteAll() {
        service.deleteAll();
        Assertions.assertAll(
                () -> Assertions.assertFalse(service.findAll().contains(cardList)),
                () -> Assertions.assertFalse(service.findAll().contains(cardList1))
        );
    }
}
