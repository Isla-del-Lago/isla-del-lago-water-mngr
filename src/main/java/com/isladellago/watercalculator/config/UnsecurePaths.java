package com.isladellago.watercalculator.config;

import java.util.HashMap;
import java.util.Map;

/**
 * This class holds all unsecure paths that should not be validated
 * with a JWT token.
 */
public final class UnsecurePaths {

    private static final Map<String, String> UNSECURE_PATHS = new HashMap<>();

    public static Map<String, String> getUnsecurePaths() {

        UNSECURE_PATHS.put("/water/health", "GET");

        return UNSECURE_PATHS;
    }

}
