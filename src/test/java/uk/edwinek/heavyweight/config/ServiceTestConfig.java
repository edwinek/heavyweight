package uk.edwinek.heavyweight.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import uk.edwinek.heavyweight.parser.Parser;
import uk.edwinek.heavyweight.persistence.ReignRepository;

@Configuration
@ComponentScan({"uk.edwinek.heavyweight.service"})
public class ServiceTestConfig {

    @Bean
    public ReignRepository reignRepository() {
        return Mockito.mock(ReignRepository.class);
    }

    @Bean
    public Parser parser() {
        return Mockito.mock(Parser.class);
    }

}
