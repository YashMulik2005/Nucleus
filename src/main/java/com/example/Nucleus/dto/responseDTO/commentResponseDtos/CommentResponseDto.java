package com.example.Nucleus.dto.responseDTO.commentResponseDtos;

import java.time.LocalDateTime;

public class CommentResponseDto {
    private Long id;
    private String message;
    private LocalDateTime createdAt;
    private Long userId;
    private String userName;

    public CommentResponseDto() {
    }

    public CommentResponseDto(Long id, String message, LocalDateTime createdAt, Long userId, String userName) {
        this.id = id;
        this.message = message;
        this.createdAt = createdAt;
        this.userId = userId;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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
