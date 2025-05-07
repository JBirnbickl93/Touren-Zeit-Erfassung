package com.birnbickl.Touren_Zeit_Erfassung.Service;

import com.birnbickl.Touren_Zeit_Erfassung.Entity.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

// Diese Klasse beinhaltet die Methode um das JWT zu generieren, sowie die Methode zur Verschlüsselung.
@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    public String generateToken(UserEntity user){
        System.out.println("JWT SECRET = " + secret);
        System.out.println("JWT EXPIRATION = " + jwtExpiration);

        return Jwts.builder().setSubject(user.getUsername()).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSigningkey(), SignatureAlgorithm.HS256)
                .compact();




    }

    // Diese Methode erstellt den kryptographischen Schlüssel zum Signieren von JWTs basierend auf dem
    // geheimen String (secret).

    private Key getSigningkey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
