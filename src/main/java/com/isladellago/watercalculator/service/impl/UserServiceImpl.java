package com.isladellago.watercalculator.service.impl;

import com.isladellago.watercalculator.domain.model.User;
import com.isladellago.watercalculator.domain.model.UserRepository;
import com.isladellago.watercalculator.exception.UserNotFoundException;
import com.isladellago.watercalculator.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) {
        log.info("[Get user by email] Method start, email: {}", email);
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }
}
