package com.birnbickl.Service;

import com.birnbickl.Entity.UserEntity;
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
import java.util.HashMap;
import java.util.Map;

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
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", user.getRole());

        return Jwts.builder().setClaims(claims)
                .setSubject(user.getUsername()).setIssuedAt(new Date())
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
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // Methode um Token zu validieren
    public Boolean isTokenValid(String token, UserDetails user){
    return extractAllClaims(token).getExpiration().before(new Date());
    }

    // Methode um User Rolle zu extrahieren
    public String extractUserRole(String Token){
        return extractAllClaims(Token).get("role", String.class);
    }
}
