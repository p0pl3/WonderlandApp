package com.example.toyshop.security;

import com.example.toyshop.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import net.minidev.json.JSONObject;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;


@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public boolean isTokenValid(String token) {
        RestTemplate restTemplate = new RestTemplate();
        JSONObject request = new JSONObject();
        request.put("token", token);
        System.out.println(token);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(request.toString(), headers);
        //ResponseEntity<String> response = restTemplate.exchange("http://api:8000/api/v1/auth/verify", HttpMethod.POST, entity, String.class);
        return true;
    }

    public User parseClaims(String token) {
        Claims payload = extractAllClaims(token);
        User user = new User();
        user.setId(payload.get("user_id", Long.class));
        user.setEmail(payload.get("email", String.class));
        user.setStatus(Status.ACTIVE);
        System.out.println(user);
        if (payload.get("is_admin", Boolean.class)) {
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.USER);
        }

        System.out.println(user);

        return user;
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(SECRET_KEY.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
