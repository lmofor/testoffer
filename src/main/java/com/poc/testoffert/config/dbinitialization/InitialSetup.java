package com.poc.testoffert.config.dbinitialization;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.poc.testoffert.domain.Role;
import com.poc.testoffert.domain.User;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Creates the initial database setup.
 */
@ChangeLog(order = "001")
public class InitialSetup {


    @ChangeSet(order = "01", author = "leon", id = "01-addRole")
    public void addRoles(MongoTemplate mongoTemplate) {
        Role adminRole = new Role();
        adminRole.setRole("ROLE_ADMIN");
        Role userRole = new Role();
        userRole.setRole("ROLE_USER");
        mongoTemplate.save(adminRole);
        mongoTemplate.save(userRole);
    }

    @ChangeSet(order = "02", author = "leon", id = "02-addUsers")
    public void addUsers(MongoTemplate mongoTemplate) {
        Role adminRole = new Role();
        adminRole.setRole("ROLE_ADMIN");
        Role userRole = new Role();
        userRole.setRole("ROLE_USER");

        User user1 = new User();
        user1.setId("user-1");
        user1.setLogin("lmofor");
        user1.setPassword("$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K");//pass = user
        user1.setFirstName("Leon");
        user1.setLastName("Mofor");
        user1.setFullName("Leon Mofor");
        user1.setEmail("leon@localhost.local");
        user1.setEnabled(true);
        user1.setLanguageCode("en");
        user1.setBirthDate(Date.from(Instant.now().minus(20,
                ChronoUnit.YEARS)));
        user1.setRegistrationDate(Instant.now());
        user1.getRoles().add(adminRole);
        user1.getRoles().add(userRole);
        mongoTemplate.save(user1);

        User user2 = new User();
        user2.setId("user-2");
        user2.setLogin("ddidier");
        user2.setPassword("$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K"); //pass = user
        user2.setFirstName("Test");
        user2.setLastName("Offert");
        user2.setFullName("Test Offert");
        user2.setEmail("offert@localhost.local");
        user2.setEnabled(true);
        user2.setLanguageCode("fr");
        user2.setBirthDate(Date.from(Instant.now().minus(25,
                ChronoUnit.YEARS)));
        user2.setRegistrationDate(Instant.now());
        mongoTemplate.save(user2);

        User user3 = new User();
        user3.setId("user-3");
        user3.setLogin("ddidier");
        user3.setPassword("$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K"); //pass = user
        user3.setFirstName("Test");
        user3.setLastName("Offert");
        user3.setFullName("Test Offert");
        user3.setEmail("offert@localhost.local");
        user3.setEnabled(true);
        user3.setLanguageCode("fr");
        user3.setBirthDate(Date.from(Instant.now().minus(25,
                ChronoUnit.YEARS)));
        user3.setRegistrationDate(Instant.now());
        mongoTemplate.save(user3);
        user3.getRoles().add(adminRole);
        user3.getRoles().add(userRole);
        mongoTemplate.save(user3);

        User user4 = new User();
        user4.setId("user-4");
        user4.setLogin("user");
        user4.setPassword("$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K"); //pass = user
        user4.setFirstName("Last");
        user4.setLastName("User");
        user4.setEmail("user@localhost.local");
        user4.setEnabled(true);
        user4.setLanguageCode("fr");
        user4.setBirthDate(Date.from(Instant.now().minus(25,
                ChronoUnit.YEARS)));
        user4.setRegistrationDate(Instant.now());
        mongoTemplate.save(user4);
        user4.getRoles().add(userRole);
        mongoTemplate.save(user4);
    }
}
