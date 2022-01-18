package spd.trello.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import spd.trello.domain.Member;
import spd.trello.domain.User;
import spd.trello.domain.enumerations.Role;
import spd.trello.repository.MemberRepository;
import spd.trello.repository.UserRepository;

import java.util.List;

class MemberServiceTest extends BaseTest {

    private final MemberService service = new MemberService(new MemberRepository(dataSource));
    private final UserService userService = new UserService(new UserRepository(dataSource));

    User user = userService.create("Karl", "Milligan", "test@horoshego.net");
    User user1 = userService.create("Karl", "Milligan", "test@horoshego.net");
    Member member = service.create("user", user.getId());
    Member member1 = service.create("user1", user1.getId());

    @Test
    void create() {
        Assertions.assertNotNull(member);
        Assertions.assertAll(
                () -> Assertions.assertEquals("user", member.getCreatedBy()),
                () -> Assertions.assertEquals(user.getId(), member.getUser())
        );
    }

    @Test
    void update() {
        Member updatedMember = service.update(member.getId(), user.getEmail(), Role.MEMBER);
        Assertions.assertAll(
                () -> Assertions.assertEquals(user.getEmail(), updatedMember.getUpdatedBy()),
                () -> Assertions.assertEquals(Role.MEMBER, updatedMember.getRole())
        );
    }

    @Test
    void findAll() {
        List<Member> members = service.findAll();
        Assertions.assertAll(
                () -> Assertions.assertTrue(members.contains(member)),
                () -> Assertions.assertTrue(members.contains(member1))
        );
    }

    @Test
    void findByID() {
        service.findByID(member.getId());
        Assertions.assertAll(
                () -> Assertions.assertEquals("user", member.getCreatedBy()),
                () -> Assertions.assertEquals(user.getId(), member.getUser())
        );
    }

    @Test
    void deleteByID() {
        service.deleteByID(member.getId());
        Assertions.assertAll(
                () -> Assertions.assertTrue(service.deleteByID(member.getId())),
                () -> Assertions.assertThrows(IllegalStateException.class, () -> service.findByID(member.getId()))
        );
    }

    @Test
    void deleteAll() {
        service.deleteAll();
        Assertions.assertAll(
                () -> Assertions.assertFalse(service.findAll().contains(member)),
                () -> Assertions.assertFalse(service.findAll().contains(member1))
        );
    }
}