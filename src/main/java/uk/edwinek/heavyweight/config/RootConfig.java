package uk.edwinek.heavyweight.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MongoConfig.class, ServiceConfig.class})
public class RootConfig {
}
