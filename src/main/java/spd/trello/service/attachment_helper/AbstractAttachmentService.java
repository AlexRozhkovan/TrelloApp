package spd.trello.service.attachment_helper;

import org.springframework.web.multipart.MultipartFile;
import spd.trello.domain.Attachment;
import spd.trello.repository.AttachmentRepository;

import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.UUID;

public abstract class AbstractAttachmentService implements CommonAttachmentService {

    AttachmentRepository repository;

    public AbstractAttachmentService(AttachmentRepository repository) {
        this.repository = repository;
    }

    public Attachment convert(MultipartFile file, UUID cardId) {
        Attachment attachment = new Attachment();
        attachment.setCardId(cardId);
        attachment.setName(file.getOriginalFilename());
        attachment.setContext(file.getContentType());
        return attachment;
    }

    @Override
    public Attachment readById(UUID id) {
        try {
            return Optional.ofNullable(repository.findById(id).orElseThrow(FileNotFoundException::new)).get();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }
}