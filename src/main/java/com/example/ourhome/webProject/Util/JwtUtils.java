package com.example.ourhome.webProject.Util;

import com.example.ourhome.webProject.controller.UserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;

public class JwtUtils {


    private final static SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String createJwtToken(UserDetail detail){
        Claims claims = Jwts.claims();

        claims.put("userid",detail.getUser().getUserid());
        claims.put("username",detail.getUsername());

        return Jwts.builder().setClaims(claims).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (60000 * 30 * 24)))
                .signWith(key).compact();
    }
    public static Claims getUsername(String token){
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }
}
