package com.example.ehdee.heavyweight.config;

import com.example.ehdee.heavyweight.persistence.ReignRepository;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses =  {ReignRepository.class})
public class MongoConfig extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "ReignsDB";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient("mongoip");
    }
}
