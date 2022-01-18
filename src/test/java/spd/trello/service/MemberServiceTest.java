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

    private final MemberService service;
    private final UserService userService = new UserService(new UserRepository(dataSource));

    MemberServiceTest() {
        service = new MemberService(new MemberRepository(dataSource));

    }

    @Test
    void create() {
        User user = userService.create("Karl", "Milligan", "test@horoshego.net");
        Member member = service.create("user", user.getId());
        Assertions.assertNotNull(member);
        Assertions.assertAll(
                () -> Assertions.assertEquals("user", member.getCreatedBy()),
                () -> Assertions.assertEquals(user.getId(), member.getUser())
        );
    }

    @Test
    void update() {
        User user = userService.create("Karl", "Milligan", "test@horoshego.net");
        Member member = service.create("user", user.getId());
        Member updatedMember = service.update( member.getId(),user.getEmail(), Role.MEMBER );
        Assertions.assertAll(
                () -> Assertions.assertEquals(user.getEmail(), updatedMember.getUpdatedBy()),
                () -> Assertions.assertEquals(Role.MEMBER, updatedMember.getRole())
        );
    }

    @Test
    void findAll() {
        User user = userService.create("Karl", "Milligan", "test@horoshego.net");
        User user1 = userService.create("Karl", "Milligan", "test@horoshego.net");
        Member member = service.create("user", user.getId());
        Member member1 = service.create("user1", user1.getId());
        List<Member> members = service.findAll();
        Assertions.assertAll(
                () -> Assertions.assertTrue(members.contains(member)),
                () -> Assertions.assertTrue(members.contains(member1))
        );
    }

    @Test
    void findByID() {
        User user = userService.create("Karl", "Milligan", "test@horoshego.net");
        Member member = service.create("user", user.getId());
        service.findByID(member.getId());
        Assertions.assertAll(
                () -> Assertions.assertEquals("user", member.getCreatedBy()),
                () -> Assertions.assertEquals(user.getId(), member.getUser())
        );
    }

    @Test
    void deleteByID() {
        User user = userService.create("Karl", "Milligan", "test@horoshego.net");
        Member member = service.create("user", user.getId());
        service.deleteByID(member.getId());
        Assertions.assertAll(
                () -> Assertions.assertTrue(service.deleteByID(member.getId())),
                () -> Assertions.assertThrows(IllegalStateException.class, ()->service.findByID(member.getId()))
        );
    }

    @Test
    void deleteAll() {
        User user = userService.create("Karl", "Milligan", "test@horoshego.net");
        User user1 = userService.create("Karl", "Milligan", "test@horoshego.net");
        Member member = service.create("user", user.getId());
        Member member1 = service.create("user1", user1.getId());
        service.deleteAll();
        Assertions.assertAll(
                () -> Assertions.assertFalse(service.findAll().contains(member)),
                () -> Assertions.assertFalse(service.findAll().contains(member1))
        );
    }
}