package spd.trello.service.attachment;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import spd.trello.domain.Attachment;
import spd.trello.repository.AttachmentRepository;

import java.io.IOException;
import java.util.UUID;

@Component
public class AttachmentDBService extends AbstractAttachmentService{

    public AttachmentDBService(AttachmentRepository repository) {
        super(repository);
    }

    @Override
    public Attachment load(UUID cardId, MultipartFile file) {
        Attachment attachment = convert(cardId, file);
        try {
            attachment.setFile(file.getBytes());
            return repository.save(attachment);
        } catch (IOException e) {
            throw new IllegalArgumentException("File not added to db", e);
        }
    }
}
