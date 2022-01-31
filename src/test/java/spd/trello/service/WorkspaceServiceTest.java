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

        Workspace workspace = new Workspace();
        workspace.setName("TestName");
        workspace.setDescription("TestDesc");
        workspace.setVisibility(WorkspaceVisibility.PUBLIC);
        service.create(workspace);

        Assertions.assertNotNull(workspace);
        Assertions.assertAll(
                () -> Assertions.assertEquals("TestName", workspace.getName()),
                () -> Assertions.assertEquals("TestDesc", workspace.getDescription()),
                () -> Assertions.assertEquals(WorkspaceVisibility.PUBLIC, workspace.getVisibility())
        );
    }

    @Test
    void update() {
        Workspace workspace = new Workspace();
        workspace.setName("TestName");
        workspace.setDescription("TestDesc");
        workspace.setVisibility(WorkspaceVisibility.PUBLIC);
        service.create(workspace);
        workspace.setName("UpdatedName");
        workspace.setDescription("UpdatedDesc");
        workspace.setVisibility(WorkspaceVisibility.PRIVATE);
                service.update(workspace);
        Assertions.assertAll(
                () -> Assertions.assertEquals("UpdatedName", workspace.getName()),
                () -> Assertions.assertEquals("UpdatedDesc", workspace.getDescription()),
                () -> Assertions.assertEquals(WorkspaceVisibility.PRIVATE, workspace.getVisibility())
        );
    }

    @Test
    void findByID() {
        Workspace workspace = new Workspace();
        workspace.setName("TestName");
        workspace.setDescription("TestDesc");
        workspace.setVisibility(WorkspaceVisibility.PUBLIC);
        service.create(workspace);
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
        Workspace workspace = new Workspace();
        workspace.setName("TestName");
        workspace.setDescription("TestDesc");
        workspace.setVisibility(WorkspaceVisibility.PUBLIC);
        service.create(workspace);

        Workspace workspace1 = new Workspace();
        workspace1.setName("TestName1");
        workspace1.setDescription("TestDesc1");
        workspace1.setVisibility(WorkspaceVisibility.PUBLIC);
        service.create(workspace1);
        Assertions.assertAll(
                () -> Assertions.assertTrue(service.findAll().contains(workspace)),
                () -> Assertions.assertTrue(service.findAll().contains(workspace1))
        );
    }

    @Test
    void deleteByID() {
        Workspace workspace = new Workspace();
        workspace.setName("TestName");
        workspace.setDescription("TestDesc");
        workspace.setVisibility(WorkspaceVisibility.PUBLIC);
        service.create(workspace);
        service.deleteByID(workspace.getId());
        Assertions.assertFalse(service.findAll().contains(workspace));

    }
}
