package com.poc.testoffert.config;


//import com.github.mongobee.Mongobee;
import com.github.cloudyrock.spring.v5.EnableMongock;
import com.poc.testoffert.event.UserCascadeSaveMongoEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;




@Configuration
@EnableMongock
@EnableMongoRepositories("com.poc.testoffert.repository")
@Import(value = MongoAutoConfiguration.class)
public class MongoConfig {

    private final Logger log = LoggerFactory.getLogger(MongoConfig.class);

    @Bean
    public UserCascadeSaveMongoEventListener userCascadingMongoEventListener(MongoOperations mongoOperations) {
        return new UserCascadeSaveMongoEventListener(mongoOperations);
    }


}
