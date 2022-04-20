package spd.trello.service.attachment;

import org.springframework.web.multipart.MultipartFile;
import spd.trello.domain.Attachment;

import java.util.UUID;

public interface CommonAttachmentService {

    Attachment load(UUID cardId, MultipartFile file);

    Attachment readById(UUID id);

    void deleteById(UUID id);

}
