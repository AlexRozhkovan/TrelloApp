package spd.trello.service.attachment;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spd.trello.configuration.StorageLocation;
import spd.trello.domain.Attachment;


import java.io.IOException;
import java.util.UUID;

@Service
public class AttachmentService {

    private AttachmentDBService dbService;
    private AttachmentLocalService localService;
    private StorageLocation storage;

    public AttachmentService(StorageLocation storage, AttachmentDBService dbService, AttachmentLocalService localService) {
        this.storage = storage;
        this.dbService = dbService;
        this.localService = localService;
    }

    public Attachment save(MultipartFile file) throws IOException {
        if (storage.getLocation().equals("db"))
            return dbService.load(file);
        return localService.load(file);
    }

    public Attachment readById(UUID id) {
        if (storage.getLocation().equals("db"))
            return dbService.readById(id);
        return localService.readById(id);

    }
}