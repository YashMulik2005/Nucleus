package com.example.Nucleus.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class SucessResponseHandler {
    public static ResponseEntity<Object> SucessResponseBuilder(HttpStatus httpStatus, boolean status,
                                                        String message, Object body){
        Map<String, Object> mp = new HashMap<>();
        mp.put("status", status);
        mp.put("message", message);
        mp.put("data", body);

        return new ResponseEntity<>(mp, httpStatus);
    }
}
