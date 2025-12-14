package com.example.Nucleus.service;

import com.example.Nucleus.dto.requestDto.projectRequestDtos.ProjectRequestDto;
import com.example.Nucleus.dto.responseDTO.projectResponseDtos.ProjectJoinCodeResponse;
import com.example.Nucleus.dto.responseDTO.projectResponseDtos.ProjectResponseDto;
import com.example.Nucleus.dto.responseDTO.AuthResponseDtos.UserShortResponseDto;

import java.util.List;

public interface ProjectService {
    ProjectResponseDto addProject(ProjectRequestDto projectRequestDto);
    ProjectJoinCodeResponse generateProjectCode(Long id);
    ProjectResponseDto joinProject(String code);
    List<UserShortResponseDto> getProjectUsers(Long id);
    ProjectResponseDto UpdateProject(Long id, ProjectRequestDto projectRequestDto);
    void deleteProject(Long id);
}
