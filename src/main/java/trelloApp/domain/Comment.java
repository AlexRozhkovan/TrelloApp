package trelloApp.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class Comment {
    private String text;
    private List<Attachment> attachments;
    private Member member;
    private LocalDateTime date;
}
