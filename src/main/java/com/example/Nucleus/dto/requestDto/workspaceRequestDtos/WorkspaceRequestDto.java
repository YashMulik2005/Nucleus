package com.example.Nucleus.dto.requestDto.workspaceRequestDtos;

public class WorkspaceRequestDto {
    private String name;

    public WorkspaceRequestDto() {
    }

    public WorkspaceRequestDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
