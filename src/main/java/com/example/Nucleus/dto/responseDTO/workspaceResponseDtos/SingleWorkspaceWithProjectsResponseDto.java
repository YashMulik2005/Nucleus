package com.example.Nucleus.dto.responseDTO.workspaceResponseDtos;

import com.example.Nucleus.dto.responseDTO.projectResponseDtos.ProjectShortResponseDto;

import java.time.LocalDateTime;
import java.util.List;

public class SingleWorkspaceWithProjectsResponseDto {
    private Long id;
    private String name;
    private LocalDateTime created_at;
    private String userName;
    private Long userId;
    private List<ProjectShortResponseDto> projects;

    public SingleWorkspaceWithProjectsResponseDto() {
    }

    public SingleWorkspaceWithProjectsResponseDto(Long id, String name, LocalDateTime created_at, String userName, Long user_id, List<ProjectShortResponseDto> projects) {
        this.id = id;
        this.name = name;
        this.created_at = created_at;
        this.userName = userName;
        this.userId = user_id;
        this.projects = projects;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<ProjectShortResponseDto> getProjects() {
        return projects;
    }

    public void setProjects(List<ProjectShortResponseDto> projects) {
        this.projects = projects;
    }
}
