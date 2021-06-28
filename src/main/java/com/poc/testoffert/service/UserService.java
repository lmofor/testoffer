package com.poc.testoffert.service;


import com.poc.testoffert.domain.User;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.poc.testoffert.domain.User}.
 */
public interface UserService {

    User save(User user);

    Optional<User> findOneByEmailIgnoreCase(String email);

    Optional<User> findOneByLoginIgnoreCase(String login);


}
