package bw5team1.epicenergyservices.security;

import bw5team1.epicenergyservices.entities.Utente;
import bw5team1.epicenergyservices.exceptions.UnauthorizedException;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTools {
    @Value("${jwt.secret}")
    private String secret;

    public String createToken(Utente utente){
        return Jwts.builder()
                .issuedAt(
                        new Date(System.currentTimeMillis())).expiration(
                        new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .subject(
                        String.valueOf(utente.getId()))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public void verifyToken(String token){
        try {
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build().parse(token);

        } catch (Exception ex) {
            throw new UnauthorizedException("There are some problems with your token. Please sign in again!");

        }
    }

    public String extractIdFromToken(String token){
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build().parseSignedClaims(token).getPayload().getSubject();
    }
}

