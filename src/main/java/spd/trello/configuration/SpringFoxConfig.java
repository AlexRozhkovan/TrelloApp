package spd.trello.configuration;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import spd.trello.domain.Reminder;
import spd.trello.repository.ReminderRepository;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.time.LocalDateTime;
import java.util.List;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

@Configuration
@EnableScheduling
public class SpringFoxConfig {

    private final TypeResolver resolver;

    private final ReminderRepository repository;

    public SpringFoxConfig(TypeResolver resolver, ReminderRepository repository) {
        this.resolver = resolver;
        this.repository = repository;
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().alternateTypeRules(
                        newRule(resolver.resolve(Sort.class), resolver.resolve(List.class, String.class)));
    }

    @Scheduled(fixedRate = 60000)
    public void RemindMessage() {
        List<Reminder> reminders = repository.findAllByRemindOnBetween(LocalDateTime.now().minusMinutes(1), LocalDateTime.now());
        for (Reminder reminder : reminders) {
            System.out.println("Reminder: " + reminder.getId() + "has been activated");
        }
    }
}