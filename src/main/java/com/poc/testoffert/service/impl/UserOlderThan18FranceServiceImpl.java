package com.poc.testoffert.service.impl;

import com.poc.testoffert.domain.User;
import com.poc.testoffert.service.UserOlderThan18FranceService;
import com.poc.testoffert.service.UserService;
import io.undertow.util.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserOlderThan18FranceServiceImpl implements UserOlderThan18FranceService {

    private final Logger log = LoggerFactory.getLogger(UserOlderThan18FranceServiceImpl.class);

    private final UserService userService;

    public UserOlderThan18FranceServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User registerUser(User user) throws BadRequestException {
        log.debug("Request to register User Older Than 18 leaving in France : {}", user);
        if(user.getAge()<=18 || !user.getCountry().equals("France"))
            throw new BadRequestException("User should be older than 18 and should leave in France");

        return userService.save(user);
    }
}
