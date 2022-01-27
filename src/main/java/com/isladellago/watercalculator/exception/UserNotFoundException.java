package com.isladellago.watercalculator.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserNotFoundException extends RuntimeException {

    private String email;

    public UserNotFoundException(String email) {
        super();
        this.email = email;
    }
}
