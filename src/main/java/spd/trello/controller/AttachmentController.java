package spd.trello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spd.trello.domain.Attachment;
import spd.trello.service.attachment_helper.MainAttachmentService;

import java.util.UUID;

@RestController
@RequestMapping("/attachments")
public class AttachmentController {

    private MainAttachmentService service;

    @Autowired
    public AttachmentController(MainAttachmentService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> upload(@RequestParam("file") MultipartFile file, @RequestParam("id") UUID id) {
        try {
            Attachment attachment = service.save(file, id);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            " attachment id = \"" + attachment.getId()
                                    + "\" filename = \"" + attachment.getName()
                                    + "\", foreign key = \"" + attachment.getCardId() + "\"")
                    .contentType(MediaType.valueOf(attachment.getContext()))
                    .body(attachment.getFile());
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not upload the file! " + e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getAttachment(@PathVariable UUID id) {
        Attachment attachment = service.readById(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        " attachment id = \"" + attachment.getId()
                                + "\" filename = \"" + attachment.getName()
                                + "\", foreign key = \"" + attachment.getCardId() + "\"")
                .contentType(MediaType.valueOf(attachment.getContext()))
                .body(attachment.getFile());
    }
}