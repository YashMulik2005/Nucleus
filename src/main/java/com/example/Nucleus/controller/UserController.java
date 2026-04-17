package com.example.Nucleus.controller;

import com.example.Nucleus.dto.SucessResponseHandler;
import com.example.Nucleus.dto.requestDto.userRequestDtos.UserProfileUpdateRequestDto;
import com.example.Nucleus.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> updateUserProfile(@PathVariable Long id, @RequestBody UserProfileUpdateRequestDto userProfileUpdateRequestDto){
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK, true,
                "Profile updated successfully.", userServiceImpl.updateBasicDetails(id, userProfileUpdateRequestDto));
    }
    @PatchMapping("/profile-img/{id}")
    public ResponseEntity<Object> updateProfileImg(@PathVariable Long id, @RequestPart("image") MultipartFile image){
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK, true,
                "Profile updated successfully.", userServiceImpl.updateProfileImg(id, image));
    }
    
}
