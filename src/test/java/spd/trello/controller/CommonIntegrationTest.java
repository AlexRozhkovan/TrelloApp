package spd.trello.controller;

import org.springframework.test.web.servlet.MvcResult;
import spd.trello.domain.parent_classes.Resource;

import java.util.UUID;

public interface CommonIntegrationTest<E extends Resource> {

    MvcResult create(String urlTemplate, E entity) throws Exception;

    MvcResult getAll(String urlTemplate) throws Exception;

    MvcResult getById(String urlTemplate, UUID id) throws Exception;

    MvcResult delete(String urlTemplate, UUID id) throws Exception;

    MvcResult update(String urlTemplate, UUID id, E entity) throws Exception;
}
