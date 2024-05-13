package com.pbl5.helpers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtVerify {
    private static String token;

    public JwtVerify(String token) {
        JwtVerify.token = token;
    }

    public static DecodedJWT verifyingJWT(String token) {
        try {

            return JWT.decode(token);
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
