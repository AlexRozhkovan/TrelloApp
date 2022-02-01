package spd.trello.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spd.trello.domain.User;
import spd.trello.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController extends AbstractController<User, UserService> {

    public UserController(UserService service) {
        super(service);
    }

    @GetMapping("/firstName/{firstName}")
    public ResponseEntity<User> readByFirstName(@PathVariable String firstName) {
        List<User> result = service.findByFirstName(firstName);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}