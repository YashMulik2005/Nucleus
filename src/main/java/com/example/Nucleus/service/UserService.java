package com.example.Nucleus.service;

import com.example.Nucleus.dto.responseDTO.UserResponseDtos.UserResponseDto;

public interface UserService {
    UserResponseDto getUserById(Long id);
}
