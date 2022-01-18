package spd.trello.repository.mapper;

import spd.trello.domain.Member;
import spd.trello.domain.User;
import spd.trello.domain.enumerations.Role;
import spd.trello.repository.UserRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class MemberMapper {

    public Member extractFromResultSet(ResultSet resultSet) throws SQLException {
        Member member = new Member();
        member.setId(UUID.fromString(resultSet.getString("id")));
        member.setCreatedBy(resultSet.getString("created_by"));
        member.setUpdatedBy(resultSet.getString("updated_by"));
        member.setCreatedDate(resultSet.getTimestamp("created_date").toLocalDateTime());
        member.setRole(Role.valueOf(resultSet.getString("role")));
        member.setUser(UUID.fromString(resultSet.getString("user_id")));
        return member;
    }
}
