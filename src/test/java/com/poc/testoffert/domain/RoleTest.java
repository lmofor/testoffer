package com.poc.testoffert.domain;


import com.poc.testoffert.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RoleTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Role.class);
        Role role1 = new Role();
        role1.setId("role-1");
        role1.setRole("Testeur");
        Role role2 = new Role();
        role2.setId(role1.getId());
        role2.setRole(role1.getRole());
        assertThat(role1).isEqualTo(role2);
        role2.setId("role-2");
        role2.setRole("developpeur");
        assertThat(role1).isNotEqualTo(role2);
        role1.setId(null);
        assertThat(role1).isNotEqualTo(role2);
    }
}
