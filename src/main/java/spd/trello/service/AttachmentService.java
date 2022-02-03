package spd.trello.service;

import org.springframework.stereotype.Service;
import spd.trello.domain.Attachment;
import spd.trello.repository.AttachmentRepository;

import java.util.List;
import java.util.UUID;

@Service
public class AttachmentService extends AbstractService<Attachment, AttachmentRepository> {

    public AttachmentService(AttachmentRepository repository) {
        super(repository);
    }

    public Attachment create(Attachment entity) {
        return super.save(entity);
    }

    public Attachment update(Attachment entity) {
        return super.update(entity);
    }

    public List<Attachment> findAll() {
        return super.getAll();
    }

    public Attachment findByID(UUID id) {
        return super.getById(id);
    }

    public void deleteByID(UUID id) {
        super.deleteById(id);
    }
}