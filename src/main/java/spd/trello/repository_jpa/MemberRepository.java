package spd.trello.repository_jpa;

import org.springframework.stereotype.Repository;
import spd.trello.domain.Member;

@Repository
public interface MemberRepository extends CommonRepository<Member> {
}