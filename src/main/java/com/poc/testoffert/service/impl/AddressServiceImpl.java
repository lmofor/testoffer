package com.poc.testoffert.service.impl;


import com.poc.testoffert.domain.Address;
import com.poc.testoffert.repository.AddressRepository;
import com.poc.testoffert.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service Interface for managing {@link com.poc.testoffert.domain.Address}.
 */
@Service
public class AddressServiceImpl implements AddressService {

    private final Logger log = LoggerFactory.getLogger(AddressServiceImpl.class);

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address save(Address address) {
        log.debug("REST request to save Address : {}", address);
        return addressRepository.save(address);
    }
}
