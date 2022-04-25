package spd.trello.configuration;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.EnableScheduling;
import spd.trello.repository.ReminderRepository;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

@Configuration
@EnableScheduling
public class SwaggerConfig {

    private final TypeResolver resolver;

    private final ReminderRepository repository;

    public SwaggerConfig(TypeResolver resolver, ReminderRepository repository) {
        this.resolver = resolver;
        this.repository = repository;
    }

    @Bean
    public Docket createDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("spd.trello"))
                .build()
                .alternateTypeRules(
                        newRule(resolver.resolve(Sort.class), resolver.resolve(List.class, String.class)));
    }
}