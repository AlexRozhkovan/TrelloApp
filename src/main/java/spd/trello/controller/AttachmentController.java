package spd.trello.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spd.trello.domain.Attachment;
import spd.trello.service.AttachmentService;

@RestController
@RequestMapping(value = "/attachments", produces = MediaType.APPLICATION_JSON_VALUE)
public class AttachmentController extends AbstractController<Attachment, AttachmentService> {

    public AttachmentController(AttachmentService service) {
        super(service);
    }
}