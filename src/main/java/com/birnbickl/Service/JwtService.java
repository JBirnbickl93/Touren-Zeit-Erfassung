package com.birnbickl.Touren_Zeit_Erfassung.Service;

import com.birnbickl.Touren_Zeit_Erfassung.Entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

// Diese Klasse beinhaltet die Methode um das JWT zu generieren
// sowie die Methoden zur Verschlüsselung, Validerung und Username-Extrahierung aus dem Token
@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    // Methode zur Generierung eines JWT
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

    // Hilfsmethode für extractUsername und isTokenValid
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSigningkey()).build().parseClaimsJws(token).getBody();
    }

    // Methode um den Usernamen zu extrahieren
    private String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Methode um Token zu validieren
    private Boolean isTokenValid(String token, UserDetails user){
    return extractAllClaims(token).getExpiration().before(new Date());
    }
}
