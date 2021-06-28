package com.poc.testoffert.service.impl;

import com.poc.testoffert.domain.User;
import com.poc.testoffert.repository.UserRepository;
import com.poc.testoffert.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        log.debug("Request to save User : {}", user);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findOneByEmailIgnoreCase(String email) {
        log.debug("Request to get User By email : {}", email);
        return userRepository.findOneByEmailIgnoreCase(email);
    }

    @Override
    public Optional<User> findOneByLoginIgnoreCase(String login) {
        log.debug("Request to get User By login : {}", login);
        return userRepository.findOneByLoginIgnoreCase(login);
    }

}
