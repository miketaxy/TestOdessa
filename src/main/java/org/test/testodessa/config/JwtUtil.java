package org.test.testodessa.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.test.testodessa.model.User;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {
    public static final SecretKey SECRET_KEY = getSigningKey();


    public static String generateToken(User user) {
        return Jwts
                .builder()
                .subject(user.getUsername())
                .signWith(SECRET_KEY)
                .expiration(new Date(System.currentTimeMillis() + 864000000))
                .compact();
    }
    private static SecretKey getSigningKey(){
        return Jwts.SIG.HS256.key().build();
    }


    public static Claims parseToken(String token){
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
