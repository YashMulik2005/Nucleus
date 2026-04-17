package com.example.Nucleus.service.impl;

import com.example.Nucleus.dto.requestDto.userRequestDtos.UserProfileUpdateRequestDto;
import com.example.Nucleus.dto.responseDTO.UserResponseDtos.UserResponseDto;
import com.example.Nucleus.exception.NotFoundException;
import com.example.Nucleus.exception.ParameterMissingException;
import com.example.Nucleus.exception.UserNotFoundException;
import com.example.Nucleus.model.User;
import com.example.Nucleus.repository.UserRepository;
import com.example.Nucleus.service.UserService;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private S3ServiceImpl s3ServiceImpl;

    @Override
    @Cacheable(cacheNames = "userData" , key = "#id")
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("User not found with this id."));

        UserResponseDto res = modelMapper.map(user, UserResponseDto.class);
        if(res.getProfile_img() != null) {
            res.setProfile_img(s3ServiceImpl.getImgUrl(res.getProfile_img()));
        }
        return res;
    }

    @Override
    @CachePut(cacheNames = "userData", key = "#id")
    public UserResponseDto updateBasicDetails(Long id , UserProfileUpdateRequestDto userProfileUpdateRequestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("User not found this this id"));

        user.setDisplayName(userProfileUpdateRequestDto.getDisplayName());
        user.setFname(userProfileUpdateRequestDto.getFname());
        user.setLname(userProfileUpdateRequestDto.getLname());

        User updatedUser = userRepository.save(user);

        UserResponseDto res = modelMapper.map(updatedUser, UserResponseDto.class);
        if(res.getProfile_img() != null) {
            res.setProfile_img(s3ServiceImpl.getImgUrl(res.getProfile_img()));
        }
        return res;
    }

    @Override
    @CachePut(cacheNames = "userData", key = "#id")
    public UserResponseDto updateProfileImg(Long id, MultipartFile img) {
        if (img == null || img.isEmpty()) {
            throw new ParameterMissingException("Image file is required");
        }
        User user = userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("User not found this this id"));

        if(!img.isEmpty()){
            String key = s3ServiceImpl.uploadImg(img, "profile");
            user.setProfile_img(key);
            System.out.println("key is ready and imahe added.");
        }

        User updatedUser = userRepository.save(user);

        UserResponseDto res = modelMapper.map(updatedUser, UserResponseDto.class);
        res.setProfile_img(s3ServiceImpl.getImgUrl(res.getProfile_img()));
        return res;
    }

}
