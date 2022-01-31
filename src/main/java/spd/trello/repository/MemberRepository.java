package spd.trello.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import spd.trello.domain.Member;
import spd.trello.domain.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface MemberRepository extends IRepository<Member> {

    List<Member> findByUser(User user);

}
