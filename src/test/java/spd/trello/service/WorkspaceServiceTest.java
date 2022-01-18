package spd.trello.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import spd.trello.domain.Workspace;
import spd.trello.domain.enumerations.WorkspaceVisibility;
import spd.trello.repository.WorkspaceRepository;

class WorkspaceServiceTest extends BaseTest {

    private final WorkspaceService service = new WorkspaceService(new WorkspaceRepository(dataSource));


    Workspace workspace = service.create("user", "TestName", "TestDesc", WorkspaceVisibility.PUBLIC);
    Workspace workspace1 = service.create("user1", "TestName1", "TestDesc1", WorkspaceVisibility.PUBLIC);

    @Test
    void create() {

        Assertions.assertNotNull(workspace);
        Assertions.assertAll(
                () -> Assertions.assertEquals("user", workspace.getCreatedBy()),
                () -> Assertions.assertEquals("TestName", workspace.getName()),
                () -> Assertions.assertEquals("TestDesc", workspace.getDescription()),
                () -> Assertions.assertEquals(WorkspaceVisibility.PUBLIC, workspace.getVisibility())
        );
    }

    @Test
    void update() {
        Workspace updatedWorkspace = service.update(workspace.getId(), "updateUser", "UpdateName", "UpdateDesc", WorkspaceVisibility.PRIVATE);
        Assertions.assertAll(
                () -> Assertions.assertEquals("UpdateName", updatedWorkspace.getName()),
                () -> Assertions.assertEquals("UpdateDesc", updatedWorkspace.getDescription()),
                () -> Assertions.assertEquals(WorkspaceVisibility.PRIVATE, updatedWorkspace.getVisibility())
        );
    }

    @Test
    void findByID() {
        service.findByID(workspace.getId());
        Assertions.assertAll(
                () -> Assertions.assertEquals("user", workspace.getCreatedBy()),
                () -> Assertions.assertEquals("TestName", workspace.getName()),
                () -> Assertions.assertEquals("TestDesc", workspace.getDescription()),
                () -> Assertions.assertEquals(WorkspaceVisibility.PUBLIC, workspace.getVisibility())
        );
    }

    @Test
    void findAll() {
        Assertions.assertAll(
                () -> Assertions.assertTrue(service.findAll().contains(workspace)),
                () -> Assertions.assertTrue(service.findAll().contains(workspace1))
        );
    }

    @Test
    void deleteByID() {
        Assertions.assertEquals(service.findByID(workspace.getId()), workspace);
        service.deleteByID(workspace.getId());

        Assertions.assertAll(
                () -> Assertions.assertTrue(service.deleteByID(workspace.getId())),
                () -> Assertions.assertThrows(IllegalStateException.class, () -> service.findByID(workspace.getId()))
        );
    }

    @Test
    void deleteAll() {
        service.deleteAll();
        Assertions.assertAll(
                () -> Assertions.assertFalse(service.findAll().contains(workspace)),
                () -> Assertions.assertFalse(service.findAll().contains(workspace1))
        );
    }
}