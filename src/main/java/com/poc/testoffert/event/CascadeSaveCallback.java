package com.poc.testoffert.event;

import com.poc.testoffert.aop.annotation.CascadeSave;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Collection;

public class CascadeSaveCallback implements ReflectionUtils.FieldCallback {
    private Object source;
    private MongoOperations mongoOperations;

    CascadeSaveCallback(final Object source, final MongoOperations mongoOperations) {
        this.source = source;
        this.setMongoOperations(mongoOperations);
    }

    @Override
    public void doWith(final Field field) throws IllegalArgumentException, IllegalAccessException {
        ReflectionUtils.makeAccessible(field);

       // System.out.println("CASCADEUR : "+ field.getName());
        if (field.isAnnotationPresent(DBRef.class) && field.isAnnotationPresent(CascadeSave.class)) {
            final Object fieldValue = field.get(getSource());

            if (fieldValue != null) {
                final IdentifierCallback callback = new IdentifierCallback();

                ReflectionUtils.doWithFields(fieldValue.getClass(), callback);
                if (fieldValue instanceof Collection<?>) {
                    //System.out.println("CASCADEUR COLLECTION : "+ field.getName());
                    ((Collection<?>) fieldValue).forEach(mongoOperations::save);
                } else {
                    ReflectionUtils.doWithFields(fieldValue.getClass(), callback);
                    mongoOperations.save(fieldValue);
                }

            }
        }

    }

    private Object getSource() {
        return source;
    }

    public void setSource(final Object source) {
        this.source = source;
    }

    private MongoOperations getMongoOperations() {
        return mongoOperations;
    }

    private void setMongoOperations(final MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }
}
