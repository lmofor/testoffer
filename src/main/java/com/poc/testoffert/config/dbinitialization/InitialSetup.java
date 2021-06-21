package com.poc.testoffert.config.dbinitialization;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.mongock.driver.mongodb.springdata.v3.decorator.impl.MongockTemplate;
import com.poc.testoffert.domain.Role;
import com.poc.testoffert.domain.User;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * Creates the initial database setup.
 */
@ChangeLog(order = "001")
public class InitialSetup {




    @ChangeSet(order = "01", author = "leon", id = "01-addRole")
    public void addRoles(MongockTemplate mongockTemplate) {
        Role adminRole = new Role();
        adminRole.setRole("ROLE_ADMIN");
        Role userRole = new Role();
        userRole.setRole("ROLE_USER");
        mongockTemplate.save(adminRole);
        mongockTemplate.save(userRole);
    }

    @ChangeSet(order = "02", author = "leon", id = "02-addUsers")
    public void addUsers(MongockTemplate mongockTemplate) {


        User user1 = new User();
        user1.setId("user-1");
        user1.setLogin("lmofor");
        user1.setFirstName("Leon");
        user1.setLastName("Mofor");
        user1.setFullName("Leon Mofor");
        user1.setEmail("leon@localhost.local");
        user1.setEnabled(true);
        user1.setLanguageCode("en");
        user1.setAge(38);
        user1.setRegistrationDate(Instant.now());
        mongockTemplate.save(user1);

        User user2 = new User();
        user2.setId("user-2");
        user2.setLogin("ddidier");
        user2.setFirstName("Test");
        user2.setLastName("Offert");
        user2.setFullName("Test Offert");
        user2.setEmail("offert@localhost.local");
        user2.setEnabled(true);
        user2.setLanguageCode("fr");
        user2.setAge(25);
        user2.setRegistrationDate(Instant.now());
        mongockTemplate.save(user2);

        User user3 = new User();
        user3.setId("user-3");
        user3.setLogin("ddidier");
        user3.setFirstName("Test");
        user3.setLastName("Offert");
        user3.setFullName("Test Offert");
        user3.setEmail("ddidier@localhost.local");
        user3.setEnabled(true);
        user3.setLanguageCode("fr");
        user3.setAge(19);
        user3.setRegistrationDate(Instant.now());
        mongockTemplate.save(user3);
        mongockTemplate.save(user3);

        User user4 = new User();
        user4.setId("user-4");
        user4.setLogin("user");
        user4.setFirstName("Last");
        user4.setLastName("User");
        user4.setEmail("user@localhost.local");
        user4.setEnabled(true);
        user4.setLanguageCode("fr");
        user4.setAge(20);
        user4.setRegistrationDate(Instant.now());
        mongockTemplate.save(user4);
        mongockTemplate.save(user4);
    }
}
