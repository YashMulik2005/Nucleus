package com.example.Nucleus.service.impl;

import com.example.Nucleus.dto.requestDto.authRequestDtos.LoginRequestDto;
import com.example.Nucleus.dto.requestDto.authRequestDtos.SignUpRequestDto;
import com.example.Nucleus.dto.responseDTO.LoginResponseDTO;
import com.example.Nucleus.dto.responseDTO.AuthResponseDtos.SignupResponseDto;
import com.example.Nucleus.exception.NotFoundException;
import com.example.Nucleus.exception.ParameterMissingException;
import com.example.Nucleus.exception.UserAlreadyExist;
import com.example.Nucleus.model.RefreshToken;
import com.example.Nucleus.model.User;
import com.example.Nucleus.repository.RefreshTokenRepository;
import com.example.Nucleus.repository.UserRepository;
import com.example.Nucleus.security.JwtUtils;
import io.jsonwebtoken.JwtException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    public SignupResponseDto signUp(SignUpRequestDto signUpRequestDto){
        User user = userRepository.findByEmail(signUpRequestDto.getEmail())
                .orElse(null);

        if(user != null) throw new UserAlreadyExist("User with email alreday exist.");
        User requestData =  modelMapper.map(signUpRequestDto, User.class);
        requestData.setPassword(passwordEncoder.encode(signUpRequestDto.getPassword()));
        User savedUser = userRepository.save(requestData);

        return modelMapper.map(savedUser, SignupResponseDto.class);
    }

    @Transactional
    public LoginResponseDTO login(LoginRequestDto loginRequestDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        String accessToken = jwtUtils.generateAccessToken(user);
        String refreshToken = jwtUtils.generateRefreshToken(user);

        RefreshToken storedToken = refreshTokenRepository.findByUserIdAndExpired(user.getId(),
                        false).orElse(null);

        if(storedToken != null) {
            storedToken.setExpired(true);
            refreshTokenRepository.save(storedToken);
        }

        LocalDateTime expiresAt = LocalDateTime.now().plusDays(10);
        RefreshToken requestRefreshToken = new RefreshToken(refreshToken, false, user, expiresAt);
        refreshTokenRepository.save(requestRefreshToken);

        return new LoginResponseDTO(user.getId(), accessToken, refreshToken);
    }

    @Transactional
    public LoginResponseDTO refreshToken(String refreshToken){
        if(refreshToken == null || refreshToken.isBlank() || refreshToken.isEmpty()){
            throw new ParameterMissingException("Required Parameters are missing.");
        }

        RefreshToken storedToken = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(()-> new NotFoundException("Refresh token not found."));

        if(storedToken.getExpired()) throw new JwtException("Refresh token is expired.");

        Date now = new Date();
        if(storedToken.getExpireAt().isBefore(LocalDateTime.now())) throw new JwtException("Refresh token is expired.");

        User user = storedToken.getUser();
        String newAccessToken = jwtUtils.generateAccessToken(user);
        String newRefreshToken = jwtUtils.generateRefreshToken(user);
        LocalDateTime expiresAt = LocalDateTime.now().plusDays(10);

        storedToken.setExpired(true);
        refreshTokenRepository.save(storedToken);

        RefreshToken newTokenToStore = new RefreshToken(newRefreshToken, false,user,expiresAt);
        refreshTokenRepository.save(newTokenToStore);
        return new LoginResponseDTO(user.getId(), newAccessToken, newRefreshToken);
    }
}
