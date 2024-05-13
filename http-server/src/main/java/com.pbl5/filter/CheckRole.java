package com.pbl5.filter;

import com.pbl5.helpers.JwtVerify;
import com.pbl5.utils.constants.Endpoint;

import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;


public class CheckRole {
    public static boolean check(HttpServletRequest req, Object... roles) {
//        System.out.println(roles[0].equals(decoded));
        try {

            String decoded = Objects.requireNonNull(JwtVerify.verifyingJWT(req.getHeader("ACCESS_TOKEN"))).getClaim("role").asString();
//            System.out.println("decode + " +decoded);
            for (Object role : roles) {
                if (role.equals(decoded)) return true;
            }
            return false;
        } catch (Exception e) {
//            e.printStackTrace();
            return false;
        }
    }
}
