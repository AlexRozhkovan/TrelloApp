package spd.trello.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import spd.trello.domain.User;
import spd.trello.domain.Workspace;
import spd.trello.domain.enumerations.WorkspaceVisibility;
import spd.trello.repository.UserRepository;
import spd.trello.repository.WorkspaceRepository;

public class UserServiceTest extends BaseTest{

    private final UserService service = new UserService(new UserRepository(dataSource));


    User user = service.create("Jonh", "Smith", "google@emaila.net");
    User user1 = service.create("Sam", "Johnson", "google@emaila.net");
    @Test
    void create() {

        Assertions.assertNotNull(user);
        Assertions.assertAll(
                () -> Assertions.assertEquals("Jonh", user.getFirstName()),
                () -> Assertions.assertEquals("Smith", user.getLastName()),
                () -> Assertions.assertEquals("google@emaila.net", user.getEmail())
        );
    }

    @Test
    void update() {
        User updatedUser = service.update( user.getId(),"updateFirstName", "UpdatedLastName", "UpdateEmail");
        Assertions.assertAll(
                () -> Assertions.assertEquals("updateFirstName", updatedUser.getFirstName()),
                () -> Assertions.assertEquals("UpdatedLastName", updatedUser.getLastName()),
                () -> Assertions.assertEquals("UpdateEmail", updatedUser.getEmail())
        );
    }

    @Test
    void findByID() {
        service.findByID(user.getId());
        Assertions.assertAll(
                () -> Assertions.assertEquals("Jonh", user.getFirstName()),
                () -> Assertions.assertEquals("Smith", user.getLastName()),
                () -> Assertions.assertEquals("google@emaila.net", user.getEmail())
        );
    }

    @Test
    void findAll(){
        Assertions.assertAll(
                () -> Assertions.assertTrue(service.findAll().contains(user)),
                () -> Assertions.assertTrue(service.findAll().contains(user1))
        );
    }

    @Test
    void deleteByID() {
        Assertions.assertEquals(service.findByID(user.getId()), user);
        service.deleteByID(user.getId());

        Assertions.assertAll(
                () -> Assertions.assertTrue(service.deleteByID(user.getId())),
                () -> Assertions.assertThrows(IllegalStateException.class, ()->service.findByID(user.getId()))
        );
    }

    @Test
    void deleteAll() {
        service.deleteAll();
        Assertions.assertAll(
                () -> Assertions.assertFalse(service.findAll().contains(user)),
                () -> Assertions.assertFalse(service.findAll().contains(user1))
        );
    }
}
