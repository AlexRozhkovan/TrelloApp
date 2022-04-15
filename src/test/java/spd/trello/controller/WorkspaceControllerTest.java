package spd.trello.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MvcResult;
import spd.trello.TrelloApplication;
import spd.trello.domain.Workspace;
import spd.trello.repository.WorkspaceRepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = TrelloApplication.class)
@AutoConfigureMockMvc
public class WorkspaceControllerTest extends AbstractControllerTest<Workspace> {

    private static String URL = "/workspaces";

    @Autowired
    private WorkspaceRepository repository;

    @Test
    @DisplayName("readAll")
    public void successReadAll() throws Exception {
        MvcResult result = super.getAll(URL);

        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus())
        );
    }


    @Test
    @DisplayName("create")
    public void successCreate() throws Exception {
        Workspace expected = EntityBuilder.buildWorkspace();
        MvcResult result = super.create(URL, expected);

        assertAll(
                () -> assertNotNull(getValue(result, "$.createdDate")),
                () -> assertNotNull(getValue(result, "$.createdBy")),
                () -> assertEquals(expected.getName(), getValue(result, "$.name")),
                () -> assertEquals(expected.getDescription(), getValue(result, "$.description"))
        );
    }

    @Test
    @DisplayName("update")
    public void successUpdate() throws Exception {
        Workspace expected = EntityBuilder.getWorkspace(repository);
        expected.setName("test2");
        expected.setDescription("test2");

        MvcResult result = super.update(URL, expected);

        assertAll(
                () -> assertNotNull(getValue(result, "$.updatedDate")),
                () -> assertNotNull(getValue(result, "$.updatedBy")),
                () -> assertEquals(expected.getName(), getValue(result, "$.name")),
                () -> assertEquals(expected.getDescription(), getValue(result, "$.description"))
        );
    }

    @Test
    @DisplayName("readById")
    public void successReadById() throws Exception {
        Workspace expected = new Workspace();
        expected.setId(UUID.fromString("7ee897d3-9065-471d-53bd-7ad5f30c5bd4"));

        MvcResult result = super.readById(URL, expected.getId());
        assertAll(
                () -> assertNotNull(getValue(result, "$.createdDate")),
                () -> assertNotNull(getValue(result, "$.createdBy"))
        );

    }

    @Test
    @DisplayName("failReadById")
    public void failReadById() throws Exception {
        MvcResult result = super.readById(URL, UUID.randomUUID());
        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
    }

    @Test
    @DisplayName("delete")
    public void successDelete() throws Exception {
        Workspace expected = EntityBuilder.getWorkspace(repository);
        MvcResult result = super.delete(URL, expected.getId());

        assertAll(
                () -> assertNotNull(getValue(result, "$.createdDate")),
                () -> assertNotNull(getValue(result, "$.createdBy")),
                () -> assertEquals(expected.getName(), getValue(result, "$.name")),
                () -> assertEquals(expected.getDescription(), getValue(result, "$.description"))
        );
    }

    @Test
    @DisplayName("failDelete")
    public void failDelete() throws Exception {
        MvcResult result = super.delete(URL, UUID.randomUUID());
        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
    }
}