package com.poc.testoffert.domain;

import com.poc.testoffert.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class AddressTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Address.class);
        Address address1 = new Address();
        address1.setId("address-1");
        address1.setPhone("7566998");
        address1.setTown("Dakar");
        address1.setStreet("Liberte");

        Address address2 = new Address();
        address2.setId(address1.getId());
        address2.setPhone(address1.getPhone());
        address2.setTown(address1.getTown());
        address2.setStreet(address1.getStreet());

        assertThat(address1).isEqualTo(address2);
        address2.setId("address-2");
        assertThat(address1).isNotEqualTo(address2);
        address1.setId(null);
        assertThat(address1).isNotEqualTo(address2);
    }
}
