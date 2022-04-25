package spd.trello.service.attachment_helper;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spd.trello.configuration.AttachmentStorage;
import spd.trello.domain.Attachment;

import java.io.IOException;
import java.util.UUID;

@Service
public class MainAttachmentService {

    private AttachmentDBService dbService;
    private AttachmentLocalService localService;
    private AttachmentStorage storage;

    public MainAttachmentService(AttachmentStorage storage, AttachmentDBService dbService, AttachmentLocalService localService) {
        this.storage = storage;
        this.dbService = dbService;
        this.localService = localService;
    }

    public Attachment save(MultipartFile file, UUID cardId) throws IOException {
        if (storage.getLocation().equals("src/main/resources/downloads"))
            return localService.load(file, cardId);
        return dbService.load(file, cardId);
    }

    public Attachment readById(UUID id) {
        if (storage.getLocation().equals("src/main/resources/downloads"))
            return localService.readById(id);
        return dbService.readById(id);

    }
}