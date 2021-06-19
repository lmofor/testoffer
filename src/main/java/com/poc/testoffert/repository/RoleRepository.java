package com.poc.testoffert.repository;


import com.poc.testoffert.domain.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data MongoDB repository for the {@link Role} entity.
 */
@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

    Optional<Role> findOneByRole(String role);

}
