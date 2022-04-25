package spd.trello.repository_jpa;

import org.springframework.stereotype.Repository;
import spd.trello.domain.Comment;

@Repository
public interface CommentRepository extends CommonRepository<Comment> {
}