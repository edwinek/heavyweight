package uk.edwinek.heavyweight.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "uk.edwinek.heavyweight.service",
        "uk.edwinek.heavyweight.parser"
})
public class ServiceConfig {
}
