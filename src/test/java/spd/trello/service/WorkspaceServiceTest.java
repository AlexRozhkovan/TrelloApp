package spd.trello.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spd.trello.domain.Workspace;
import spd.trello.domain.enumerations.WorkspaceVisibility;

@SpringBootTest
class WorkspaceServiceTest extends BaseTest {

    @Autowired
    WorkspaceService service;

    @Test
    void create() {
        Workspace workspace = service.create("user", "TestName", "TestDesc", WorkspaceVisibility.PUBLIC);
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
        Workspace workspace = service.create("user", "TestName", "TestDesc", WorkspaceVisibility.PUBLIC);
        Workspace updatedWorkspace = service.update(workspace.getId(), "updateUser", "UpdateName", "UpdateDesc", WorkspaceVisibility.PRIVATE);
        Assertions.assertAll(
                () -> Assertions.assertEquals("UpdateName", updatedWorkspace.getName()),
                () -> Assertions.assertEquals("UpdateDesc", updatedWorkspace.getDescription()),
                () -> Assertions.assertEquals(WorkspaceVisibility.PRIVATE, updatedWorkspace.getVisibility())
        );
    }

    @Test
    void findByID() {
        Workspace workspace = service.create("user", "TestName", "TestDesc", WorkspaceVisibility.PUBLIC);
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
        Workspace workspace = service.create("user", "TestName", "TestDesc", WorkspaceVisibility.PUBLIC);
        Workspace workspace1 = service.create("user1", "TestName1", "TestDesc1", WorkspaceVisibility.PUBLIC);
        Assertions.assertAll(
                () -> Assertions.assertTrue(service.findAll().contains(workspace)),
                () -> Assertions.assertTrue(service.findAll().contains(workspace1))
        );
    }

    @Test
    void deleteByID() {
        Workspace workspace = service.create("user", "TestName", "TestDesc", WorkspaceVisibility.PUBLIC);
        Assertions.assertTrue(service.deleteByID(workspace.getId()));

    }
}
