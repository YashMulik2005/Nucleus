package com.example.Nucleus.service.impl;

import com.example.Nucleus.dto.responseDTO.UserResponseDtos.UserResponseDto;
import com.example.Nucleus.exception.NotFoundException;
import com.example.Nucleus.exception.UserNotFoundException;
import com.example.Nucleus.model.User;
import com.example.Nucleus.repository.UserRepository;
import com.example.Nucleus.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("User not found with this id."));

        return modelMapper.map(user, UserResponseDto.class);
    }


}
