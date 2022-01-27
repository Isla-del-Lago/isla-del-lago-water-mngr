package com.isladellago.watercalculator.service;

public interface TokenService {

    boolean validate(String token);

    String getEmailFromToken(String token);
}
