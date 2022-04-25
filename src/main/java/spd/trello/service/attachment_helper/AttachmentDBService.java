package spd.trello.service.attachment_helper;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import spd.trello.domain.Attachment;
import spd.trello.repository_jpa.AttachmentRepository;

import java.io.IOException;
import java.util.UUID;

@Component
public class AttachmentDBService extends AbstractAttachmentService {

    public AttachmentDBService(AttachmentRepository repository) {
        super(repository);
    }

    @Override
    public Attachment load(MultipartFile file, UUID cardId) {
        Attachment attachment = convert(file, cardId);
        try {
            attachment.setFile(file.getBytes());
            return repository.save(attachment);
        } catch (IOException e) {
            throw new IllegalArgumentException("File not added to db", e);
        }
    }
}