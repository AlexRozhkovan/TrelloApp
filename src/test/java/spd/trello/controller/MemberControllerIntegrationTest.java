package spd.trello.controller;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import spd.trello.domain.Member;
import spd.trello.domain.enumerations.Role;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MemberControllerIntegrationTest extends AbstractIntegrationTest<Member> {

    String URL_TEMPLATE = "/members";

    @Test
    void getByIdFailure() throws Exception {
        UUID id = UUID.randomUUID();
        MvcResult result = super.getById(URL_TEMPLATE, id);
        assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
    }

    @Test
    void getByIdSuccess() throws Exception {
        Member entity = new Member();
        entity.setRole(Role.OWNER);
        entity.setUserId(UUID.randomUUID());
        MvcResult ent = super.create(URL_TEMPLATE, entity);
        UUID id = UUID.fromString(JsonPath.read(ent.getResponse().getContentAsString(), "$.id"));
        MvcResult result = super.getById(URL_TEMPLATE, id);
        Assertions.assertNotNull(getValue(result, "$.id"));
    }

    @Test
    void deleteSuccess() throws Exception {
        Member entity = new Member();
        entity.setRole(Role.OWNER);
        entity.setUserId(UUID.randomUUID());

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
        Member entity = new Member();
        entity.setRole(Role.OWNER);
        entity.setUserId(UUID.randomUUID());
        MvcResult result = super.create(URL_TEMPLATE, entity);
        assertAll(
                () -> assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus()),
                () -> assertNotNull(getValue(result, "$.id")),
                () -> assertEquals(entity.getRole().toString(), getValue(result, "$.role")),
                () -> assertEquals(entity.getUserId().toString(), getValue(result, "$.userId"))
        );
    }

    @Test
    void createFailed() throws Exception {
        Member entity = new Member();
        entity.setRole(Role.OWNER);
        MvcResult result = super.create(URL_TEMPLATE, entity);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
    }

    @Test
    void update() throws Exception {
        Member entity = new Member();
        entity.setRole(Role.OWNER);
        entity.setUserId(UUID.randomUUID());
        super.create(URL_TEMPLATE, entity);
        entity.setRole(Role.ADMIN);
        MvcResult result = super.update(URL_TEMPLATE, entity.getId(), entity);
        assertAll(
                () -> assertNotNull(getValue(result, "$.id")),
                () -> assertEquals(entity.getRole().toString(), getValue(result, "$.role")));
    }

    @Test
    void updateFailed() throws Exception {
        Member entity = new Member();
        entity.setRole(Role.OWNER);
        entity.setUserId(UUID.randomUUID());
        super.create(URL_TEMPLATE, entity);
        entity.setRole(null);
        MvcResult result = super.update(URL_TEMPLATE, entity.getId(), entity);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());
    }

    @Test
    void updateNotFound() throws Exception {
        Member entity = new Member();
        entity.setRole(Role.OWNER);
        entity.setUserId(UUID.randomUUID());
        super.create(URL_TEMPLATE, entity);
        entity.setRole(Role.ADMIN);
        MvcResult result = super.update("/wrong", entity.getId(), entity);

        Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), result.getResponse().getStatus());
    }
}