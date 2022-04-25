package spd.trello.repository_jpa;

import org.springframework.stereotype.Repository;
import spd.trello.domain.Attachment;

@Repository
public interface AttachmentRepository extends CommonRepository<Attachment> {
}