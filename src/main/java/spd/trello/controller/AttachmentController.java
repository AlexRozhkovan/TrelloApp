package spd.trello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spd.trello.domain.Attachment;
import spd.trello.service.attachment.AttachmentService;

import java.util.UUID;

@RestController
@RequestMapping("/attachments")
public class AttachmentController {

    private AttachmentService service;

    public AttachmentController(AttachmentService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> upload(@RequestParam("id") UUID id,
                                         @RequestParam("file") MultipartFile file) {
        try {
            Attachment attachment = service.save(id, file);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,  " attachment; id = \"" + attachment.getId()
                            + "\" filename = \"" + attachment.getName()
                            + "\", foreign key = \"" + attachment.getCardId() + "\"")
                    .contentType(MediaType.valueOf(attachment.getContext()))
                    .body(attachment.getFile());
        } catch (Exception e) {
            throw new IllegalArgumentException("Couldn't upload the file!" + e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getAttachment(@PathVariable UUID id) {
        Attachment attachment = service.readById(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, " attachment id = \"" + attachment.getId()
                        + "\" filename = \"" + attachment.getName()
                        + "\", foreign key = \"" + attachment.getCardId() + "\"")
                .contentType(MediaType.valueOf(attachment.getContext()))
                .body(attachment.getFile());
    }
}