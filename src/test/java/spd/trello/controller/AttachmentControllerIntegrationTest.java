package spd.trello.controller;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import spd.trello.domain.Attachment;
import spd.trello.repository.AttachmentRepository;
import spd.trello.service.attachment.AttachmentService;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AttachmentControllerIntegrationTest extends AbstractIntegrationTest<Attachment> {

    @Autowired
    AttachmentRepository repo;
    @Autowired
    AttachmentService service;

    String URL_TEMPLATE = "/attachments";

    @Test
    void getByIdFailure() throws Exception {
        UUID id = UUID.randomUUID();
        MvcResult result = super.getById(URL_TEMPLATE, id);
        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
    }

    @Test
    void getByIdSuccess() throws Exception {
        Attachment entity = new Attachment();
        entity.setName("name");
        entity.setLink("link");
        MvcResult ent = super.create(URL_TEMPLATE, entity);
        UUID id = UUID.fromString(JsonPath.read(ent.getResponse().getContentAsString(), "$.id"));
        MvcResult result = super.getById(URL_TEMPLATE, id);
        Assertions.assertNotNull(getValue(result, "$.id"));
    }

    @Test
    void deleteSuccess() throws Exception {
        Attachment entity = new Attachment();
        entity.setName("name");
        entity.setLink("link");

        MvcResult ent = super.create(URL_TEMPLATE, entity);
        UUID id = UUID.fromString(JsonPath.read(ent.getResponse().getContentAsString(), "$.id"));

        MvcResult result = super.delete(URL_TEMPLATE, id);
        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus()),
                () -> assertEquals(HttpStatus.NOT_FOUND.value(), super.getById(URL_TEMPLATE, id).getResponse().getStatus())
        );
    }

    @Test
    void deleteSuccess1() throws Exception {
        Attachment entity = new Attachment();
        entity.setName("name");
        entity.setLink("link");
        Attachment newEnt = repo.save(entity);

        MvcResult result = super.delete(URL_TEMPLATE, newEnt.getId());
        assertAll(
                () -> assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus()),
                () -> assertEquals(HttpStatus.NOT_FOUND.value(), super.getById(URL_TEMPLATE, newEnt.getId()).getResponse().getStatus())
        );
        Assertions.assertTrue(repo.findById(newEnt.getId()).isEmpty());
    }

    @Test
    void deleteFailed() throws Exception {
        UUID id = UUID.randomUUID();
        MvcResult result = super.delete(URL_TEMPLATE, id);
        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
    }

    @Test
    void getAllFailure() throws Exception {
        MvcResult result = super.getAll("/wrong");
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
    void createSuccess() throws Exception {
        Attachment entity = new Attachment();
        entity.setName("name");
        entity.setLink("link");
        MvcResult result = super.create(URL_TEMPLATE, entity);
        assertAll(
                () -> assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus()),
                () -> assertNotNull(getValue(result, "$.id")),
                () -> assertEquals(entity.getName(), getValue(result, "$.name")),
                () -> assertEquals(entity.getLink(), getValue(result, "$.link"))
        );
    }

    @Test
    void createFailed() throws Exception {
        Attachment entity = new Attachment();
        entity.setLink("link");
        MvcResult result = super.create(URL_TEMPLATE, entity);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
    }

    @Test
    void updateSuccess() throws Exception {
        Attachment entity = new Attachment();
        entity.setName("name");
        entity.setLink("link");
        super.create(URL_TEMPLATE, entity);
        entity.setName("UpdatedName");
        entity.setLink("UpdatedLink");
        MvcResult result = super.update(URL_TEMPLATE, entity.getId(), entity);
        assertAll(
                () -> assertNotNull(getValue(result, "$.id")),
                () -> assertEquals(entity.getName(), getValue(result, "$.name")),
                () -> assertEquals(entity.getLink(), getValue(result, "$.link"))
        );
    }

    @Test
    void updateFailed() throws Exception {
        Attachment entity = new Attachment();
        entity.setName("name");
        entity.setLink("link");
        super.create(URL_TEMPLATE, entity);
        entity.setName(null);
        entity.setLink("UpdatedLink");
        MvcResult result = super.update(URL_TEMPLATE, entity.getId(), entity);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
    }

    @Test
    void updateNotFound() throws Exception {
        Attachment entity = new Attachment();
        entity.setName("name");
        entity.setLink("link");
        super.create(URL_TEMPLATE, entity);
        entity.setName("UpdatedName");
        entity.setLink("UpdatedLink");
        MvcResult result = super.update("/wrong", entity.getId(), entity);

        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
    }
}