package com.example.Nucleus.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private Boolean expired;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime expireAt;

    @ManyToOne
    private User user;

    //methods
    public RefreshToken() {
    }

    public RefreshToken(Long id, String token, Boolean isExpired, LocalDateTime createdAt, LocalDateTime expireAt, User user) {
        this.id = id;
        this.token = token;
        this.expired = isExpired;
        this.createdAt = createdAt;
        this.expireAt = expireAt;
        this.user = user;
    }

    public RefreshToken(String refreshToken, boolean status, User user, LocalDateTime expireAt) {
        this.token = refreshToken;
        this.expired = status;
        this.user = user;
        this.expireAt = expireAt;
    }

    public LocalDateTime getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(LocalDateTime expireAt) {
        this.expireAt = expireAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
