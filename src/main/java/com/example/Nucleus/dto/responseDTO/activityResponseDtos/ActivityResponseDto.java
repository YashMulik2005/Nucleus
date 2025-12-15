package com.example.Nucleus.dto.responseDTO.activityResponseDtos;

import java.time.LocalDateTime;

public class ActivityResponseDto {
    private LocalDateTime createdAt;
    private String message;
    private Long userId;
    private String userName;

    public ActivityResponseDto() {
    }

    public ActivityResponseDto(LocalDateTime createdAt, String message, Long userId, String displayName) {
        this.createdAt = createdAt;
        this.message = message;
        this.userId = userId;
        this.userName = displayName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
