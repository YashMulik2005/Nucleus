package com.example.Nucleus.service;

import com.example.Nucleus.dto.requestDto.userRequestDtos.UserProfileUpdateRequestDto;
import com.example.Nucleus.dto.responseDTO.UserResponseDtos.UserResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    UserResponseDto getUserById(Long id);
    UserResponseDto updateBasicDetails(Long id, UserProfileUpdateRequestDto userProfileUpdateRequestDto);
    UserResponseDto updateProfileImg(Long id, MultipartFile img);
}
