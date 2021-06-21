package com.poc.testoffert.config;


//import com.github.mongobee.Mongobee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration
//@EnableMongoRepositories("com.poc.testoffert.repository")
//@Import(value = MongoAutoConfiguration.class)
public class Database {
    private final Logger log = LoggerFactory.getLogger(Database.class);

    @Autowired
    private Environment environment;


}
