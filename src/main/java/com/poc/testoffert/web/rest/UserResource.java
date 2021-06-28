package com.poc.testoffert.web.rest;

import com.poc.testoffert.domain.User;
import com.poc.testoffert.service.UserOlderThan18FranceService;
import com.poc.testoffert.service.UserService;
import io.undertow.util.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;


/**
 * REST controller for register users.
 * <p>
 * This class accesses the {@link User} entity, and fetches its collection of roles and addresses.
 * It also allow registering users older than 18 and leaving in France
 */

@RestController
@RequestMapping("/api")
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    @Value("${spring.application.name}")
    private String applicationName;

    private final UserService userService;
    private final UserOlderThan18FranceService userOlderThan18FranceService;


    public UserResource(UserService userService, UserOlderThan18FranceService userOlderThan18FranceService) {
        this.userService = userService;
        this.userOlderThan18FranceService = userOlderThan18FranceService;
    }


    /**
     * {@code POST  /users} : Register a new user.
     *
     * @param user the user to register.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new user, with status {@code 400 (Bad Request)} if the user has already an ID, or with status {@code 500 (Internal Server Error)} for other errors.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/users")
    public ResponseEntity<User> registerUser(@RequestBody @Valid User user) throws URISyntaxException, BadRequestException {
        log.debug("REST request to save User : {}", user);
        if (user.getId() != null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "A new user cannot already have an ID");
        }
        User result = userOlderThan18FranceService.registerUser(user);
        return ResponseEntity.created(new URI("/api/user-by-login/" + result.getLogin()))
                .body(result);
    }

    /**
     * {@code GET  /user-by-login/:login} : get the user with pathvariable login.
     *
     * @param login the login of the user to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the user, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-by-login/{login}")
    public ResponseEntity<User> getUserDetailsByLogin(@PathVariable @NotNull String login) {
        log.debug("REST request to get user details by login : {}", login);
        Optional<User> user = userService.findOneByLoginIgnoreCase(login);
        if (user.isPresent())
            return ResponseEntity.ok().body(user.get());
        return ResponseEntity.notFound().build();
    }

    /**
     * {@code GET  /user-by-email} : get the user with requestparam email.
     *
     * @param email the login of the user to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the user, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-by-email")
    public ResponseEntity<User> getUserDetailsByEmail(@RequestParam @NotNull String email) {
        log.debug("REST request to get user details by email : {}", email);
        Optional<User> user = userService.findOneByEmailIgnoreCase(email);
        if (user.isPresent())
            return ResponseEntity.ok().body(user.get());
        return ResponseEntity.notFound().build();
    }


}
