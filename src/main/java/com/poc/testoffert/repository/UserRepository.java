package com.poc.testoffert.repository;


import com.poc.testoffert.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data MongoDB repository for the {@link User} entity.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findOneByEmailIgnoreCase(String email);

    Optional<User> findOneByLoginIgnoreCase(String login);

}
