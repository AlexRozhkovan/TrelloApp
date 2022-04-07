package spd.trello.service.attachment;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import spd.trello.domain.Attachment;
import spd.trello.repository.AttachmentRepository;

import java.io.IOException;

@Component
public class AttachmentDBService extends AbstractAttachmentService{

    public AttachmentDBService(AttachmentRepository repository) {
        super(repository);
    }

    @Override
    public Attachment load(MultipartFile file) {
        Attachment attachment = convert(file);
        try {
            attachment.setFile(file.getBytes());
            return repository.save(attachment);
        } catch (IOException e) {
            throw new IllegalArgumentException("File not added to db", e);
        }
    }

}
