package spd.trello.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource("classpath:/application.yml")
public class AttachmentStorage {
    @Value("${location}")
    private String location;
}
