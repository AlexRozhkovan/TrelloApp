package spd.trello.controller;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MvcResult;
import spd.trello.domain.Label;
import spd.trello.domain.Workspace;
import spd.trello.domain.enumerations.Color;
import spd.trello.domain.enumerations.WorkspaceVisibility;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class LabelControllerIntegrationTest extends AbstractIntegrationTest<Label> {

    String URL_TEMPLATE = "/labels";

    @Test
    void getByIdFailure() throws Exception {
        UUID id = UUID.randomUUID();

        MvcResult result = super.getById(URL_TEMPLATE, id);

        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
    }

    @Test
    void getByIdSuccess() throws Exception {
        Label entity = new Label();
        entity.setName("name");
        entity.setColor(Color.RED);
        entity.setCardId(UUID.randomUUID());
        MvcResult ent = super.create(URL_TEMPLATE, entity);
        UUID id = UUID.fromString(JsonPath.read(ent.getResponse().getContentAsString(), "$.id"));
        MvcResult result = super.getById(URL_TEMPLATE, id);
        Assertions.assertNotNull(getValue(result, "$.id"));
    }

    @Test
    void deleteSuccess() throws Exception {
        Label entity = new Label();
        entity.setName("name");
        entity.setColor(Color.RED);
        entity.setCardId(UUID.randomUUID());

        MvcResult ent = super.create(URL_TEMPLATE, entity);
        UUID id = UUID.fromString(JsonPath.read(ent.getResponse().getContentAsString(), "$.id"));

        MvcResult result = super.delete(URL_TEMPLATE, id);
        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus()),
                () -> assertEquals(HttpStatus.NOT_FOUND.value(), super.getById(URL_TEMPLATE, id).getResponse().getStatus())
        );
    }

    @Test
    void deleteFailed() throws Exception {
        UUID id = UUID.randomUUID();

        MvcResult result = super.delete(URL_TEMPLATE, id);
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
    }

    @Test
    void getAllSuccess() throws Exception {
        MvcResult result = super.getAll(URL_TEMPLATE);
        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus()),
                () -> assertEquals("application/json", result.getResponse().getContentType())
        );
    }

    @Test
    void getAllFailure() throws Exception {
        MvcResult result = super.getAll("/wrong");
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
    }

    @Test
    void createSuccess() throws Exception {

        Label entity = new Label();
        entity.setName("name");
        entity.setColor(Color.RED);
        entity.setCardId(UUID.randomUUID());
        MvcResult result = super.create(URL_TEMPLATE, entity);
        assertAll(
                () -> assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus()),
                () -> assertNotNull(getValue(result, "$.id")),
                () -> assertEquals(entity.getName(), getValue(result, "$.name")),
                () -> assertEquals(entity.getColor().toString(), getValue(result, "$.color"))
        );
    }

    @Test
    void createFailed() throws Exception {

        Label entity = new Label();
        entity.setName("name");
        entity.setColor(Color.RED);
        MvcResult result = super.create(URL_TEMPLATE, entity);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
    }

    @Test
    void update() throws Exception {
        Label entity = new Label();
        entity.setName("name");
        entity.setColor(Color.RED);
        entity.setCardId(UUID.randomUUID());
        super.create(URL_TEMPLATE, entity);
        entity.setName("name");
        entity.setColor(Color.ORANGE);
        MvcResult result = super.update(URL_TEMPLATE, entity.getId(), entity);

        assertAll(
                () -> assertNotNull(getValue(result, "$.id")),
                () -> assertEquals(entity.getName(), getValue(result, "$.name")),
                () -> assertEquals(entity.getColor().toString(), getValue(result, "$.color"))
        );
    }

    @Test
    void updateFailed() throws Exception {
        Label entity = new Label();
        entity.setName("name");
        entity.setColor(Color.RED);
        entity.setCardId(UUID.randomUUID());
        super.create(URL_TEMPLATE, entity);
        entity.setName(null);
        entity.setColor(Color.ORANGE);
        MvcResult result = super.update(URL_TEMPLATE, entity.getId(), entity);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
    }

    @Test
    void updateNotFound() throws Exception {
        Label entity = new Label();
        entity.setName("name");
        entity.setColor(Color.RED);
        entity.setCardId(UUID.randomUUID());
        super.create(URL_TEMPLATE, entity);
        entity.setName("name");
        entity.setColor(Color.ORANGE);
        MvcResult result = super.update("/wrong", entity.getId(), entity);

        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
    }
}