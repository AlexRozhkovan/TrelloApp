package spd.trello.repository;

import org.springframework.stereotype.Repository;
import spd.trello.domain.Member;
import spd.trello.domain.User;

import java.util.List;

@Repository
public interface MemberRepository extends IRepository<Member> {

    List<Member> findByUser(User user);

}