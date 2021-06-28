package com.poc.testoffert.event;

import com.poc.testoffert.domain.User;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.util.ReflectionUtils;

public class UserCascadeSaveMongoEventListener extends AbstractMongoEventListener<User> {


    private MongoOperations mongoOperations;

    public UserCascadeSaveMongoEventListener(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public @Override void onBeforeSave(BeforeSaveEvent<User> event) {
        final Object source = event.getSource();
        ReflectionUtils.doWithFields(source.getClass(), new CascadeSaveCallback(source, mongoOperations));
    }


}
