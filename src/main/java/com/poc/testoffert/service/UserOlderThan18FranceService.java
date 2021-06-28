package com.poc.testoffert.service;

import com.poc.testoffert.domain.User;
import io.undertow.util.BadRequestException;

public interface UserOlderThan18FranceService {

    User registerUser(User user) throws BadRequestException;
}
