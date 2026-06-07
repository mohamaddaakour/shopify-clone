package com.shopify.security;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public class CookieService {

    // to create a cookie and put the token in it
    public void addJwtCookie(HttpServletResponse response, String token) {

        Cookie cookie = new Cookie("jwt", token);

        cookie.setHttpOnly(true);
        
        cookie.setPath("/");
        
        cookie.setMaxAge(24 * 60 * 60);

        response.addCookie(cookie);
    }

    // to clear the cookie when the user logout
    public void clearJwtCookie(HttpServletResponse response) {

        Cookie cookie = new Cookie("jwt", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        response.addCookie(cookie);
    }
}