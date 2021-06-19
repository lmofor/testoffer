package com.poc.testoffert.repository;


import com.poc.testoffert.domain.Adress;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the {@link Adress} entity.
 */
@Repository
public interface AdressRepository extends MongoRepository<Adress, String> {


}
