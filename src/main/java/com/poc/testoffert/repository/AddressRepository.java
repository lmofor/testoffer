package com.poc.testoffert.repository;


import com.poc.testoffert.domain.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the {@link Address} entity.
 */
@Repository
public interface AddressRepository extends MongoRepository<Address, String> {


}
