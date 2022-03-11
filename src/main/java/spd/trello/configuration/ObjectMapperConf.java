package spd.trello.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;

public class ObjectMapperConf {
    @Bean
    ObjectMapper obj(){
        return new ObjectMapper().findAndRegisterModules();
    }

}
