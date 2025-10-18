package com.appGobal.patient_appGobal.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class PatientJwtUtil {
    private final static String SECRETKEY ="8c2339c615267fae4028dbdda515ce239907d37216b0137fd3899cb1c3f68bb6";

    public String generateToken(UserDetails userDetails)
    {
        Map<String, Object> cliams = new HashMap<>();
        return createToken(cliams, userDetails.getUsername());
    }
    public String extractUsername( String token)
    {
        return extractCliams(token, Claims::getSubject);
    }
    public boolean validateToken(String token, UserDetails userDetails)
    {
        final String username = userDetails.getUsername();
        return username.equals(extractUsername(token))&& !isTokenExpired(token);
    }
    private boolean isTokenExpired(String token)
    {
        return extractExpiration(token).before(new Date(System.currentTimeMillis()));
    }
    private Date extractExpiration(String token)
    {
        return extractCliams(token,Claims::getExpiration);
    }
    private <T> T extractCliams(String token, Function<Claims,T> resolver)
    {
        final Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }
    private Claims extractAllClaims(String token)
    {
        return Jwts.parser()
                .decryptWith((SecretKey) getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
    private String createToken(Map<String,Object>cliams, String subject)
    {
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .subject(subject)
                .claims(cliams)
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60*60))
                // signWith deprecated to be replaced
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    private Key getSignKey()
    {
        byte[] bytes = Decoders.BASE64.decode(SECRETKEY);
        return Keys.hmacShaKeyFor(bytes);
    }
}
