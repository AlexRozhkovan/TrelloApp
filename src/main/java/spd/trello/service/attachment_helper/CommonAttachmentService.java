package spd.trello.service.attachment_helper;

import spd.trello.domain.Attachment;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface CommonAttachmentService {

    Attachment load(MultipartFile file, UUID cardId);

    Attachment readById(UUID id);
}