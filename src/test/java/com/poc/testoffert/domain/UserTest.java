package com.poc.testoffert.domain;


import com.poc.testoffert.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(User.class);
        User user1 = new User();
        user1.setId("user-1");
        user1.setLogin("user-1");
        user1.setEmail("user1@okj.fr");
        user1.setFirstName("Leon");
        user1.setLastName("Mofor");
        user1.setFullName("Leon Mofor");
        user1.setEnabled(true);
        user1.setLanguageCode("en");
        user1.setAge(38);
        user1.setRegistrationDate(Instant.now());
        User user2 = new User();
        user2.setId(user1.getId());
        user2.setEmail(user1.getEmail());
        user2.setLogin(user1.getLogin());
        user2.setFirstName(user1.getFirstName());
        user2.setLastName(user1.getLastName());
        user2.setFullName(user1.getFullName());
        user2.setEnabled(user1.isEnabled());
        user2.setLanguageCode(user1.getLanguageCode());
        user2.setAge(user1.getAge());
        user2.setRegistrationDate(user1.getRegistrationDate());
        assertThat(user1).isEqualTo(user2);
        user2.setId("user-2");
        user2.setLogin("user-2");
        user2.setEmail("user2dfsf@dfdsf.jt");
        assertThat(user1).isNotEqualTo(user2);
        user1.setId(null);
        assertThat(user1).isNotEqualTo(user2);
    }
}
