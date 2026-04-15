package com.example.Nucleus.controller;

import com.example.Nucleus.dto.SucessResponseHandler;
import com.example.Nucleus.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id){
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK, true,
                "Profile data fetched successfully.", userServiceImpl.getUserById(id));
    }

    
}
