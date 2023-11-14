package com.example.toyshop.security2;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VerifyService {

    public boolean verifyToken(String token){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8000/verify", token, String.class);
        return response.getStatusCode()== HttpStatusCode.valueOf(200);
    }


}
