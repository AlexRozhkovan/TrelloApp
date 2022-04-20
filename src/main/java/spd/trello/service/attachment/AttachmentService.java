package spd.trello.service.attachment;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spd.trello.configuration.StorageConf;
import spd.trello.domain.Attachment;


import java.io.IOException;
import java.util.UUID;

@Service
public class AttachmentService {

    private AttachmentDBService dbService;
    private AttachmentLocalService localService;
    private StorageConf storage;

    public AttachmentService(StorageConf storage, AttachmentDBService dbService, AttachmentLocalService localService) {
        this.storage = storage;
        this.dbService = dbService;
        this.localService = localService;
    }

    public Attachment save(UUID cardId, MultipartFile file) throws IOException {
        if (storage.getLocation().equals("db"))
            return dbService.load(cardId, file);
        return localService.load(cardId, file);
    }

    public Attachment readById(UUID id) {
        if (storage.getLocation().equals("db"))
            return dbService.readById(id);
        return localService.readById(id);
    }


    public void deleteById(UUID id) {
        if (storage.getLocation().equals("db"))
            dbService.deleteById(id);
        localService.deleteById(id);
    }
}