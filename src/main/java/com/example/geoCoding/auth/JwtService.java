package com.example.geoCoding.auth;


import com.example.geoCoding.dTO.PayloadAssignDto;
import com.example.geoCoding.repository.Service.CompanyRepoService;
import com.example.geoCoding.repository.Service.SubscriptionRepoService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtService {


    private static final String secret_key = "eyJhbGciOiJIUzI1NiJ9eyJSb2xlIjoiQWRtaW4iLCJJc3N1ZXIiOiJJc3N1ZXIiLCJVc2VybmFtZSI6IkphdmFJblVzZSIsImV4cCI6MTcwNjUyNDE4MiwiaWF0IjoxNzA2NTI0MTgyfQJfhY1a3tmN3UT99ORZw8ZVIRjMHCt2oNXIgH6PbKY";
    @Autowired
    private SubscriptionRepoService subscriptionRepoService;
    @Autowired
    private CompanyRepoService companyRepoService;

    public String extractUserName(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {

        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build().parseClaimsJws(token)
                .getBody();
    }

    public Boolean isTokenValid(String token, String companyName) {
        return (!isTokenExpired(token) && extractUserName(token).equals(companyName));
    }

    public Key getSignInKey() {

        byte[] value = Decoders.BASE64.decode(secret_key);
        return Keys.hmacShaKeyFor(value);
    }

    public Date getExpiryDate(String token) {
        return extractClaims(token, Claims::getExpiration);
    }


    public Boolean isTokenExpired(String token) {
        return getExpiryDate(token).before(new Date());
    }

    public String generateToken(PayloadAssignDto payloadAssignDto) {
        HashMap<String, Object> hashMap = new HashMap<>();

        return generateToken(payloadAssignDto, hashMap);
    }

    public String generateToken(PayloadAssignDto payloadAssignDto, HashMap<String, Object> claims) {
        return Jwts.builder().setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + (1000 * 15 * 60 * 60)))
                .setIssuedAt(new Date())
                .setSubject(payloadAssignDto.getCompanyName())
                .claim("companyId", payloadAssignDto.getCompanyId())
                .claim("subscriptionId", payloadAssignDto.getSubscriptionId())
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

}
