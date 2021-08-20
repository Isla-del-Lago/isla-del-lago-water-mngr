package com.isladellago.watercalculator.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class responsible for has some usefull mapper methods to use
 * in different classes and methods.
 */
public final class JacksonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JacksonUtils.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    private JacksonUtils() {
        // Empty constructor to avoid class instantiation
    }

    /**
     * This method returns a Json String from the given object.
     *
     * @param object Object to be converted.
     * @return Json string from given object.
     */
    public static final <T> String getJsonStringFromObject(T object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            LOGGER.error("[GET JSON STRING FROM OBJECT] JSON PARSING FAILED");
            return null;
        }
    }

}
