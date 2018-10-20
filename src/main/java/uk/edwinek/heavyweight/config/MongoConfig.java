package uk.edwinek.heavyweight.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import uk.edwinek.heavyweight.persistence.ReignRepository;

@Configuration
@EnableMongoRepositories(basePackageClasses = {ReignRepository.class})
public class MongoConfig extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "ReignsDB";
    }

    @Override
    public MongoClient mongoClient() {
        return new MongoClient("mongoip");
    }
}
