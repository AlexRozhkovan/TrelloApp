package trelloApp.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Comment {
    private String text;
    private List<Attachment> attachments;
    private Member member;
    private LocalDateTime date;
}
