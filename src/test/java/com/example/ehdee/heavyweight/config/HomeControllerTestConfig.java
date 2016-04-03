package com.example.ehdee.heavyweight.config;

import com.example.ehdee.heavyweight.parser.Parser;
import com.example.ehdee.heavyweight.persistence.ReignRepository;
import com.example.ehdee.heavyweight.service.Service;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.example.ehdee.heavyweight.web"})
public class HomeControllerTestConfig {

    @Bean
    public ReignRepository reignRepository(){
        return Mockito.mock(ReignRepository.class);
    }

    @Bean
    public Parser parser(){
        return Mockito.mock(Parser.class);
    }

    @Bean
    public Service soupScraper(){
        return Mockito.mock(Service.class);
    }
}
