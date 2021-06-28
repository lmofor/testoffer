package com.poc.testoffert.service.impl;

import com.poc.testoffert.domain.Role;
import com.poc.testoffert.repository.RoleRepository;
import com.poc.testoffert.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) {
        log.debug("REST request to save Role : {}", role);
        Optional<Role> roleOptional = roleRepository.findOneByRole(role.getRole());
        // if role with the same name is present, just return saved role
        if(roleOptional.isPresent())
            return roleOptional.get();
        return roleRepository.save(role);
    }
}
