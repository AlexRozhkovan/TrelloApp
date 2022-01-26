package spd.trello.service;

import org.springframework.stereotype.Service;
import spd.trello.domain.Member;
import spd.trello.domain.enumerations.Role;
import spd.trello.repository.MemberRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class MemberService extends AbstractService<Member> {

    public MemberService(MemberRepository repository) {
        super(repository);
    }

    public Member create(String createdBy, UUID user) {
        Member member = new Member();
        member.setCreatedBy(createdBy);
        member.setUser(user);
        return repository.create(member);
    }

    public Member update(UUID id, String updatedBy, Role role) {
        Member byID = repository.findById(id);
        byID.setUpdatedBy(updatedBy);
        byID.setRole(role);
        byID.setUpdatedDate(LocalDateTime.now());
        repository.update(byID);
        return byID;
    }

    public List<Member> findAll() {
        return repository.findAll();
    }

    public Member findByID(UUID id) {
        return repository.findById(id);
    }

    public boolean deleteByID(UUID id) {
        return repository.deleteByID(id);
    }
}
