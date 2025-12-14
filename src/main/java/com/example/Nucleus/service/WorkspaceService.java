package com.example.Nucleus.service;

import com.example.Nucleus.dto.requestDto.workspaceRequestDtos.WorkspaceRequestDto;
import com.example.Nucleus.dto.responseDTO.workspaceResponseDtos.SingleWorkspaceWithProjectsResponseDto;
import com.example.Nucleus.dto.responseDTO.workspaceResponseDtos.WorkspaceResponseDto;

import java.util.List;

public interface WorkspaceService {
    WorkspaceResponseDto addWorkspace(WorkspaceRequestDto workspaceRequestDto);
    List<WorkspaceResponseDto> getWorkspaceByUser(Long id);
    SingleWorkspaceWithProjectsResponseDto getWorkspaceById(Long id);
    WorkspaceResponseDto updateWorkspace(Long id, WorkspaceRequestDto workspaceRequestDto);
}
