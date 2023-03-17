package com.example.online_shop.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "ahgfyuQIGWRGQryi211iou2rho23.qiuhfow2oi";
    private static final Integer TOKEN_VALIDITY = 60 * 30 * 30;

    public static Boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsername(token);
        return userDetails.getUsername().equals(username) && !isExpired(token);

    }

    public static String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .addClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

    }

    public static String getUsername(String token) {
        return getUsernameFromClaims(getClaimsByToken(token));
    }

    public static Date expiredTime(String token) {
        return getClaimsByToken(token).getExpiration();
    }

    private static String getUsernameFromClaims(Claims claims) {
        return Objects.nonNull(claims) ? claims.getSubject() : null;
    }

    private static Claims getClaimsByToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private static boolean isExpired(String token) {
        Date expiredDate = expiredTime(token);
        return expiredDate.before(new Date());
    }
}
