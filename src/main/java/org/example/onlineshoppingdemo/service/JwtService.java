package org.example.onlineshoppingdemo.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.example.onlineshoppingdemo.config.MyUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtService {
    @Value("${security.jwt.secret-key}")
    String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    // encode
    public String encryptToken(MyUser myUser) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", myUser.getId());
        map.put("name", myUser.getName());
        map.put("age", myUser.getAge());
        map.put("email", myUser.getEmail());

        String token = Jwts.builder()
                .setClaims(map)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSigningKey())
                .compact();
        return token;
    }

    // decode
    public String decryptUserName(String token) {
        Claims body = Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return body.getOrDefault("name", "nobody").toString();
    }

    private Key getSigningKey() {
        byte[] decode = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(decode);
    }
}
