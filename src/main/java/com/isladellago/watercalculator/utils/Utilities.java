package com.isladellago.watercalculator.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

/**
 * Class responsible for hold some utilities to be used
 * through the project.
 */
public final class Utilities {

    private static final Logger LOGGER = LoggerFactory.getLogger(Utilities.class);

    public static <T> T validateOptionalResponse(String methodFormatName, String message, Optional<T> optional) {
        if (!optional.isPresent()) {
            LOGGER.error(String.format("%s %s", methodFormatName, message));
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return optional.get();
    }

}
