package spd.trello.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import spd.trello.domain.Workspace;
import spd.trello.domain.enumerations.WorkspaceVisibility;
import spd.trello.repository.WorkspaceRepository;

class WorkspaceServiceTest extends BaseTest {

    private final WorkspaceService service;

    WorkspaceServiceTest() {
        service = new WorkspaceService(new WorkspaceRepository(dataSource));
    }


    @Test
    void create() {
        Workspace workspace = service.create("user", "Testname" , "TestDesc", WorkspaceVisibility.PUBLIC);
        Assertions.assertNotNull(workspace);
        Assertions.assertAll(
                ()->Assertions.assertEquals("user", workspace.getCreatedBy()),
                ()->Assertions.assertEquals("Testname", workspace.getName()),
                ()->Assertions.assertEquals("TestDesc", workspace.getDescription()),
                ()->Assertions.assertEquals(WorkspaceVisibility.PUBLIC, workspace.getVisibility())
        );
    }

    @Test
    void deleteAll() {
    }

    @Test
    void update() {
    }

    @Test
    void findAll() {
    }

    @Test
    void print() {
    }

    @Test
    void findByID() {
        Workspace workspace = service.create("user", "Testname" , "TestDesc", WorkspaceVisibility.PRIVATE);
        Workspace workspace1 = service.findByID(workspace.getId());
        Assertions.assertAll(()->Assertions.assertEquals("user", workspace1.getCreatedBy()),
                ()->Assertions.assertEquals("Testname", workspace1.getName()),
                ()->Assertions.assertEquals("TestDesc", workspace1.getDescription()),
                ()->Assertions.assertEquals(WorkspaceVisibility.PRIVATE, workspace1.getVisibility())
        );
    }

    @Test
    void deleteByID() {
    }
}