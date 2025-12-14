package com.example.Nucleus.dto.responseDTO.projectResponseDtos;

import java.time.LocalDateTime;

public class ProjectResponseDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private String workspace;
    private Long Workspace_id;

    public ProjectResponseDto() {
    }

    public ProjectResponseDto(Long id, String name, String description, LocalDateTime createdAt, String workspace, Long workspace_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.workspace = workspace;
        Workspace_id = workspace_id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getWorkspace() {
        return workspace;
    }

    public void setWorkspace(String workspace) {
        this.workspace = workspace;
    }

    public Long getWorkspace_id() {
        return Workspace_id;
    }

    public void setWorkspace_id(Long workspace_id) {
        Workspace_id = workspace_id;
    }
}
