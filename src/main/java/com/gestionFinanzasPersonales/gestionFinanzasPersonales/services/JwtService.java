package com.gestionFinanzasPersonales.gestionFinanzasPersonales.services;

import com.gestionFinanzasPersonales.gestionFinanzasPersonales.entities.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtService {
    final int TOKEN_LIFE_TIME = 1000 * 60 * 25; // El token dura 25 minutos
    final String SECRET_KEY = "b8eQH2maP5kr8uJzG7t4WV3x6pM1sLdTaksjfaiv713p135j191pfdji02gno2ngig0dsij";

    public String getToken(Usuario user) {
        return getToken(new HashMap<>(), user);
    }

    private String getToken(Map<String, Object> extraClaims, Usuario user) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(String.format("%s,%s,%s", user.getId_usuario(), user.getEmail(), user.getAuthorities()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_LIFE_TIME))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getIdUserFromToken(String token) {
        // [0] = idUser, [1] = email, [2] = roles
        return this.getAllClaims(token).getSubject().split(",")[0];
    }

    public String getEmailFromToken(String token) {
        // [0] = idUser, [1] = email, [2] = roles
        return this.getAllClaims(token).getSubject().split(",")[1];
    }

    public boolean isTokenValid(String token, UserDetails user) {
        final String email = this.getEmailFromToken(token);
        return (email.equals(user.getUsername()) && !this.isTokenExpired(token));
    }

    private Claims getAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Date getExpirationDate(String token){
        return this.getAllClaims(token).getExpiration();
    }

    private Boolean isTokenExpired(String token){
        return getExpirationDate(token).before(new Date());
    }

}
