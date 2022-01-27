package com.isladellago.watercalculator.service;

import com.isladellago.watercalculator.domain.model.User;

public interface UserService {

    /**
     * Get an user registered on the dataabse by
     * the given email.
     *
     * @param email Email to search the user.
     * @return The founded user.
     */
    User getUserByEmail(String email);
}
