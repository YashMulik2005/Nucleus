package com.example.Nucleus.dto.requestDto.projectRequestDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProjectRequestDto {
    @NotBlank(message = "Name is required.")
    private String name;

    @NotBlank(message = "Description id required.")
    @Size(max = 256, message = "Description cannot exceed 256 characters")
    private String description;

    @NotNull(message = "Worksapce id is required.")
    private Long workspaceId;

    public Long getWorkspaceId() {
        return workspaceId;
    }

    public void setWorkspaceId(Long workspaceId) {
        this.workspaceId = workspaceId;
    }

    public ProjectRequestDto() {
    }

    public ProjectRequestDto(String name, String description, Long workspaceId) {
        this.name = name;
        this.description = description;
        this.workspaceId = workspaceId;
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
}
