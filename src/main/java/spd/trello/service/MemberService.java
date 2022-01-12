package spd.trello.service;

import spd.trello.domain.Member;
import spd.trello.domain.User;
import spd.trello.domain.enumerations.Role;
import spd.trello.repository.MemberRepository;

import java.util.List;
import java.util.UUID;

public class MemberService extends AbstractService<Member> {
    public MemberService(MemberRepository repository) {
        super(repository);
    }

    public Member create(User user) {
        Member member = new Member();
        member.setId(UUID.randomUUID());
        member.setUser(user);
        repository.create(member);
        return member;
    }

    public boolean deleteAll() {
        repository.deleteAll();
        return true;
    }

    public Member update(UUID id) {
        Member byID = repository.findByID(id);
        repository.update(byID);
        return byID;
    }

    public List<Member> findAll() {
        return repository.findAll();
    }

    public void print(Member entity) {
        System.out.println(entity);
    }

    public Member findByID(UUID id) {
        return repository.findByID(id);
    }

    public boolean deleteByID(UUID id) {
        repository.deleteByID(id);
        return true;
    }
}
