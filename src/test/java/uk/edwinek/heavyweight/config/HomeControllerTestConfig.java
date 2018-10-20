package uk.edwinek.heavyweight.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import uk.edwinek.heavyweight.parser.Parser;
import uk.edwinek.heavyweight.persistence.ReignRepository;
import uk.edwinek.heavyweight.service.Service;

@Configuration
@ComponentScan({"uk.edwinek.heavyweight.web"})
public class HomeControllerTestConfig {

    @Bean
    public ReignRepository reignRepository() {
        return Mockito.mock(ReignRepository.class);
    }

    @Bean
    public Parser parser() {
        return Mockito.mock(Parser.class);
    }

    @Bean
    public Service soupScraper() {
        return Mockito.mock(Service.class);
    }
}
