package com.example.Nucleus.dto.responseDTO.workspaceResponseDtos;


import java.time.LocalDateTime;

public class WorkspaceResponseDto {
    private Long id;
    private String name;
    private LocalDateTime created_at;
    private String userName;
    private Long user_id;

    public WorkspaceResponseDto() {
    }

    public WorkspaceResponseDto(Long id, String name, LocalDateTime created_at, String userName, Long user_id) {
        this.id = id;
        this.name = name;
        this.created_at = created_at;
        this.userName = userName;
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
