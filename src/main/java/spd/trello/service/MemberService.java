package spd.trello.service;

import org.springframework.stereotype.Service;
import spd.trello.domain.Member;
import spd.trello.exception.IsAlreadyExist;
import spd.trello.repository.MemberRepository;

import java.util.List;
import java.util.UUID;

@Service
public class MemberService extends AbstractService<Member, MemberRepository> {

    public MemberService(MemberRepository repository) {
        super(repository);
    }

    public Member create(Member entity) {
        if (isExists(entity)) {
            throw new IsAlreadyExist();
        } else {
            return super.save(entity);
        }
    }

    public Member update(Member entity) {
        return super.update(entity);
    }

    public List<Member> findAll() {
        return super.getAll();
    }

    public Member findByID(UUID id) {
        return super.getById(id);
    }

    public void deleteByID(UUID id) {
        super.deleteById(id);
    }
}