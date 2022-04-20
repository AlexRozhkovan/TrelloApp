package spd.trello.service.attachment;

import org.springframework.web.multipart.MultipartFile;
import spd.trello.domain.Attachment;
import spd.trello.exception.NotFoundException;
import spd.trello.repository.AttachmentRepository;

import java.util.Optional;
import java.util.UUID;

public abstract class AbstractAttachmentService implements CommonAttachmentService {

    AttachmentRepository repository;

    public AbstractAttachmentService(AttachmentRepository repository) {
        this.repository = repository;
    }

    public Attachment convert(UUID cardId, MultipartFile file) {
        Attachment attachment = new Attachment();
        attachment.setCardId(cardId);
        attachment.setName(file.getOriginalFilename());
        attachment.setContext(file.getContentType());
        return attachment;
    }

    @Override
    public Attachment readById(UUID id) {
            return Optional.ofNullable(repository.findById(id).orElseThrow(NotFoundException::new)).get();
    }

    @Override
    public void deleteById(UUID id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }
}
