package com.walid.SellCar_Spring.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component

public class JWTUtil {

    private Key getSigningKey(){
        String SECRET="425848F8875744548789466533157875451215488D1548F";
        byte[] keyBytes= Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private <T> T extractClaims(String token, Function<Claims,T> claimResolvers){
        final Claims claims=extractAllClaims(token);
        return claimResolvers.apply(claims);
    }
    public String extractUsername(String token){
        return extractClaims(token,Claims::getSubject);
    }
    public Date extractExpiration(String token){
        return extractClaims(token,Claims::getExpiration);
    }
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username=extractUsername(token);

        return (username.equals(userDetails.getUsername())&&!isTokenExpired(token));
    }
    private String generateToken(Map<String,Object> extractClaims,UserDetails userDetails){
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        extractClaims.put("roles", roles);
        return Jwts.builder().setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }
    public  String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<> (), userDetails);
    }



}
