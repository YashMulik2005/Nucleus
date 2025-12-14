package com.example.Nucleus.dto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ErrorResponseHandler {
    public static  ResponseEntity<Object>ErrorResponseBuilder(HttpStatus httpStatus, boolean status,
                                                       String message){
        Map<String, Object>mp = new HashMap<>();
        mp.put("status", status);
        mp.put("message", message);

        return new ResponseEntity<>(mp, httpStatus);
    }
}
