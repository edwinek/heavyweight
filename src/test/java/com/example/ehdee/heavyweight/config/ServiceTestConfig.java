package com.example.ehdee.heavyweight.config;

import com.example.ehdee.heavyweight.parser.Parser;
import com.example.ehdee.heavyweight.persistence.ReignRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.example.ehdee.heavyweight.service"})
public class ServiceTestConfig {

    @Bean
    public ReignRepository reignRepository(){
        return Mockito.mock(ReignRepository.class);
    }

    @Bean
    public Parser parser(){
        return Mockito.mock(Parser.class);
    }

}
