package spd.trello.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spd.trello.domain.User;
import spd.trello.domain.Workspace;
import spd.trello.domain.enumerations.WorkspaceVisibility;
import spd.trello.repository.UserRepository;
import spd.trello.repository.WorkspaceRepository;

@SpringBootTest
public class UserServiceTest extends BaseTest{

    @Autowired
   UserService service;

    @Test
    void create() {
        User user = service.create("Jonh", "Smith", "google@emaila.net");
        Assertions.assertNotNull(user);
        Assertions.assertAll(
                () -> Assertions.assertEquals("Jonh", user.getFirstName()),
                () -> Assertions.assertEquals("Smith", user.getLastName()),
                () -> Assertions.assertEquals("google@emaila.net", user.getEmail())
        );
    }

    @Test
    void update() {
        User user = service.create("Jonh", "Smith", "google@emaila.net");
        User updatedUser = service.update( user.getId(),"updateFirstName", "UpdatedLastName", "UpdateEmail");
        Assertions.assertAll(
                () -> Assertions.assertEquals("updateFirstName", updatedUser.getFirstName()),
                () -> Assertions.assertEquals("UpdatedLastName", updatedUser.getLastName()),
                () -> Assertions.assertEquals("UpdateEmail", updatedUser.getEmail())
        );
    }

    @Test
    void findByID() {
        User user = service.create("Jonh", "Smith", "google@emaila.net");
        service.findByID(user.getId());
        Assertions.assertAll(
                () -> Assertions.assertEquals("Jonh", user.getFirstName()),
                () -> Assertions.assertEquals("Smith", user.getLastName()),
                () -> Assertions.assertEquals("google@emaila.net", user.getEmail())
        );
    }

    @Test
    void findAll(){
        User user = service.create("Jonh", "Smith", "google@emaila.net");
        User user1 = service.create("Sam", "Johnson", "google@emaila.net");
        Assertions.assertAll(
                () -> Assertions.assertTrue(service.findAll().contains(user)),
                () -> Assertions.assertTrue(service.findAll().contains(user1))
        );
    }

    @Test
    void deleteByID() {
        User user = service.create("Jonh", "Smith", "google@emaila.net");
        Assertions.assertTrue(service.deleteByID(user.getId()));

    }
}
