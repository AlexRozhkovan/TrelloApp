package trelloApp.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
@RequiredArgsConstructor
public class Attachment {
    private String name;
    private File file;
    private String link;
}
