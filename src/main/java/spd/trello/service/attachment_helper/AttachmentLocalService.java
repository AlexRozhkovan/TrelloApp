package spd.trello.service.attachment_helper;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import spd.trello.configuration.AttachmentStorage;
import spd.trello.domain.Attachment;
import spd.trello.exception.NotFoundException;
import spd.trello.repository.AttachmentRepository;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Component
public class AttachmentLocalService extends AbstractAttachmentService {

    private Path rootLocation;

    public AttachmentLocalService(AttachmentRepository repository, AttachmentStorage storage) {
        super(repository);
        this.rootLocation = Paths.get(storage.getLocation());
    }

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Attachment load(MultipartFile file, UUID cardId) {
        Attachment attachment = convert(file, cardId);
        if (store(file)) {
            attachment.setFile(null);
            return repository.save(attachment);
        }
        throw new NotFoundException("File not converted to attachment");
    }

    public byte[] loadFromStorage(String filename) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bytes1 = new byte[1024];
        try (FileInputStream fileInputStream = new FileInputStream(rootLocation.toAbsolutePath() + "/" + filename)) {
            for (int readNum; (readNum = fileInputStream.read(bytes1)) != -1; ) {
                byteArrayOutputStream.write(bytes1, 0, readNum);
            }
        } catch (IOException ex) {
            throw new IllegalArgumentException(ex);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public boolean store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            InputStream inputStream = file.getInputStream();
            Files.copy(inputStream, this.rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    @Override
    public Attachment readById(UUID id) {
        Attachment attachment = super.readById(id);
        attachment.setFile(loadFromStorage(attachment.getName()));
        return attachment;
    }
}