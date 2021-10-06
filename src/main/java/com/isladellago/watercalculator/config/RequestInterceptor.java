package com.isladellago.watercalculator.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.isladellago.watercalculator.utils.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class intercepts all requests and validate the token
 * if the path is secured.
 */
@Component
public class RequestInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestInterceptor.class);

    @Value("${jwt.signature.secret}")
    private String keySecret;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) {

        final String path = request.getRequestURI();
        final String method = request.getMethod();

        LOGGER.info("[REQUESTED PATH] " + path + " WITH METHOD: " + method);

        final boolean isSecurePath =
                !method.equals(UnsecurePaths.getUnsecurePaths().get(path));

        if (isSecurePath) {
            final String jwtToken = request.getHeader("X-AccessToken");

            validateToken(jwtToken);
        }

        return true;
    }

    /**
     * This method validates the JWT token given as header on the request.
     *
     * @param jwtToken JWT Token to be validated.
     */
    private void validateToken(String jwtToken) {
        final Algorithm algorithm = Algorithm.HMAC256(keySecret);

        LOGGER.info("[VERIFY JWT TOKEN] TOKEN TO BE VALIDATED " + jwtToken);

        try {
            final JWTVerifier jwtVerifier = JWT.require(algorithm).build();
            final DecodedJWT decodedJWT = jwtVerifier.verify(jwtToken);

            LOGGER.info("[JWT DECODED] " + JacksonUtils.getJsonStringFromObject(decodedJWT));
            LOGGER.info("[VERIFY JWT TOKEN] TOKEN VALIDATED");
        } catch (SignatureVerificationException ex) {
            LOGGER.error("[VERIFY JWT TOKEN] TOKEN SIGNATURE INVALID");

            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "Invalid token, please review the given token");
        } catch (JWTVerificationException ex) {
            LOGGER.error("[VERIFY JWT TOKEN] ERROR VERIFYING JWT TOKEN, ERROR: {}" +
                    JacksonUtils.getJsonStringFromObject(ex));

            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    "Invalid token, please review the given token");
        } catch (NullPointerException ex) {
            LOGGER.error("[VERIFY JWT TOKEN] TOKEN HEADER IS NULL");

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "X-AccessToken header is empty or is missing, please review the header");
        }
    }
}
