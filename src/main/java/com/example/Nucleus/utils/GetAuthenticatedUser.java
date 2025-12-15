package com.example.Nucleus.utils;

import com.example.Nucleus.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class GetAuthenticatedUser {
    public User getAuthenticatedUserDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymeousUser")){
            throw new RuntimeException("Unauthenticated.");
        }
        return (User) authentication.getPrincipal();
    }
}
