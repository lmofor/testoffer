package com.poc.testoffert.web.rest;

import com.poc.testoffert.TestoffertApplication;
import com.poc.testoffert.domain.Address;
import com.poc.testoffert.domain.Role;
import com.poc.testoffert.domain.User;
import com.poc.testoffert.repository.AddressRepository;
import com.poc.testoffert.repository.RoleRepository;
import com.poc.testoffert.repository.UserRepository;
import com.poc.testoffert.service.AddressService;
import com.poc.testoffert.service.RoleService;
import com.poc.testoffert.service.UserOlderThan18FranceService;
import com.poc.testoffert.service.UserService;
import io.undertow.util.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


//@DataMongoTest
//@DataMongoTest
//@ExtendWith(SpringExtension.class)

@SpringBootTest(classes = TestoffertApplication.class)
@AutoConfigureMockMvc
public class UserResourceIntegrationTest {

    private static final Instant REGISTRATION_DATE = Instant.parse("2021-06-29T10:11:30Z");

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserOlderThan18FranceService userOlderThan18FranceService;

    @Autowired
    private MockMvc restUserMockMvc;

    @BeforeEach
    public void initTest() {
        //System.out.println("MONGOCK CONFIGURED :"+userRepository.findOneByLoginIgnoreCase("lmofor"));
       userRepository.deleteAll();
       roleRepository.deleteAll();
       addressRepository.deleteAll();
    }


    @Test
    public void registerUserInFranceAndOlderThan18() throws Exception {
        int databaseSizeBeforeCreate = userRepository.findAll().size();

        // Create the User
        User user = new User();
        user.setLogin("test");
        user.setFirstName("Teste");
        user.setLastName("Ter");
        user.setLanguageCode("fr");
        user.setEmail("teste@tes.fr");
        user.setCountry("France");
        user.setAge(38);
        user.setRegistrationDate(REGISTRATION_DATE);
        user.setEnabled(true);
        user.setFullName("Teste Ter");

        restUserMockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(user)))
                .andExpect(status().isCreated());

        // Validate the User in the database
        assertPersistedUsers(users -> {
            assertThat(users).hasSize(databaseSizeBeforeCreate + 1);
            User testUser = users.get(users.size() - 1);
            assertThat(testUser.getLogin()).isEqualTo("test");
            assertThat(testUser.getFirstName()).isEqualTo("Teste");
            assertThat(testUser.getLastName()).isEqualTo("Ter");
            assertThat(testUser.getEmail()).isEqualTo("teste@tes.fr");
        });
    }

    @Test
    public void registerUserWithAnId() throws Exception {
        int databaseSizeBeforeCreate = userRepository.findAll().size();

        User user = new User();
        user.setId("id");
        user.setLogin("test");
        user.setFirstName("Teste");
        user.setLastName("Ter");
        user.setLanguageCode("fr");
        user.setEmail("teste@tes.fr");
        user.setCountry("France");
        user.setAge(38);
        user.setRegistrationDate(REGISTRATION_DATE);
        user.setEnabled(true);
        user.setFullName("Teste Ter");

        // An entity with an existing ID cannot be created, so this API call must fail
        restUserMockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtil.convertObjectToJsonBytes(user)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResponseStatusException));

        // Validate the User in the database
        assertPersistedUsers(users -> assertThat(users).hasSize(databaseSizeBeforeCreate));
    }

    @Test
    public void registerUserNotInFranceAndYoungerThan18() throws Exception {
        int databaseSizeBeforeCreate = userRepository.findAll().size();

        // Create the User
        User user = new User();
        user.setLogin("test");
        user.setFirstName("Teste");
        user.setLastName("Ter");
        user.setLanguageCode("fr");
        user.setEmail("teste@tes.fr");
        user.setCountry("Senegal");
        user.setAge(12);
        user.setRegistrationDate(REGISTRATION_DATE);
        user.setEnabled(true);
        user.setFullName("Teste Ter");

        try {
            userOlderThan18FranceService.registerUser(user);
        } catch (Exception e) {
        assertThat(e)
                .isInstanceOf(BadRequestException.class)
                .hasMessage("User should be older than 18 and should leave in France");
        }

        // Validate the User in the database
        assertPersistedUsers(users -> {
            assertThat(users).hasSize(databaseSizeBeforeCreate);
        });
    }

    @Test
    public void testGetExistingUserByLogin() throws Exception {
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setRole("RH");
        role = roleService.save(role);
        roles.add(role);

        Set<Address> addresses = new HashSet<>();
        Address address = new Address();
        address.setStreet("Saint-Marc");
        address.setTown("Paris");
        address.setPhone("7756866");
        address = addressService.save(address);
        addresses.add(address);

        User user = new User();
        user.setLogin("test");
        user.setFirstName("Teste");
        user.setLastName("Ter");
        user.setLanguageCode("fr");
        user.setEmail("teste@tes.fr");
        user.setCountry("France");
        user.setAge(38);
        user.setRegistrationDate(REGISTRATION_DATE);
        user.setEnabled(true);
        user.setFullName("Teste Ter");
        user.setRoles(roles);
        user.setAddresses(addresses);
        userService.save(user);

        restUserMockMvc.perform(get("/api/user-by-login/test")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.login").value("test"))
                .andExpect(jsonPath("$.firstName").value("Teste"))
                .andExpect(jsonPath("$.lastName").value("Ter"))
                .andExpect(jsonPath("$.fullName").value("Teste Ter"))
                .andExpect(jsonPath("$.age").value(38))
                .andExpect(jsonPath("$.languageCode").value("fr"))
                .andExpect(jsonPath("$.enabled").value(true))
                .andExpect(jsonPath("$.registrationDate").value(REGISTRATION_DATE.toString()))
                .andExpect(jsonPath("$.email").value("teste@tes.fr"))
                .andExpect(jsonPath("$.country").value("France"))
                .andExpect(jsonPath("$.roles.[0]").value(role))
                .andExpect(jsonPath("$.addresses.[0]").value(address));
    }

    @Test
    public void testGetNotExistingUserByLogin() throws Exception {

        restUserMockMvc.perform(get("/api/user-by-login/testch")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetExistingUserByEmail() throws Exception {
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setRole("ACCOUNT");
        role = roleService.save(role);
        roles.add(role);

        Set<Address> addresses = new HashSet<>();
        Address address = new Address();
        address.setStreet("Saint-Josue");
        address.setTown("Paris");
        address.setPhone("77567566");
        address = addressService.save(address);
        addresses.add(address);

        User user = new User();
        user.setLogin("tester");
        user.setFirstName("Testera");
        user.setLastName("Tera");
        user.setLanguageCode("fr");
        user.setEmail("tester@tes.fr");
        user.setCountry("France");
        user.setAge(34);
        user.setRegistrationDate(REGISTRATION_DATE);
        user.setEnabled(true);
        user.setFullName("Testera Tera");
        user.setRoles(roles);
        user.setAddresses(addresses);
        userService.save(user);

        restUserMockMvc.perform(get("/api/user-by-email?email=tester@tes.fr")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.login").value("tester"))
                .andExpect(jsonPath("$.firstName").value("Testera"))
                .andExpect(jsonPath("$.lastName").value("Tera"))
                .andExpect(jsonPath("$.fullName").value("Testera Tera"))
                .andExpect(jsonPath("$.age").value(34))
                .andExpect(jsonPath("$.enabled").value(true))
                .andExpect(jsonPath("$.registrationDate").value(REGISTRATION_DATE.toString()))
                .andExpect(jsonPath("$.email").value("tester@tes.fr"))
                .andExpect(jsonPath("$.country").value("France"))
                .andExpect(jsonPath("$.roles[0]").value(role))
                .andExpect(jsonPath("$.addresses[0]").value(address));
    }

    private void assertPersistedUsers(Consumer<List<User>> userAssertion) {
        userAssertion.accept(userRepository.findAll());
    }



}
