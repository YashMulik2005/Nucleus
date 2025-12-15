package com.example.Nucleus.service.impl;

import com.example.Nucleus.dto.requestDto.workspaceRequestDtos.WorkspaceRequestDto;
import com.example.Nucleus.dto.responseDTO.projectResponseDtos.ProjectShortResponseDto;
import com.example.Nucleus.dto.responseDTO.workspaceResponseDtos.SingleWorkspaceWithProjectsResponseDto;
import com.example.Nucleus.dto.responseDTO.workspaceResponseDtos.WorkspaceResponseDto;
import com.example.Nucleus.exception.NotFoundException;
import com.example.Nucleus.model.User;
import com.example.Nucleus.model.Workspace;
import com.example.Nucleus.repository.WorkspaceRepository;
import com.example.Nucleus.service.WorkspaceService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private WorkspaceRepository workspaceRepository;

    @Override
    public WorkspaceResponseDto addWorkspace(WorkspaceRequestDto workspaceRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")){
            throw new RuntimeException("User is not authenticated.");
        }
        User user = (User) authentication.getPrincipal();
        Workspace workspace = modelMapper.map(workspaceRequestDto, Workspace.class);
        workspace.setUser(user);
        Workspace savedWorkspace = workspaceRepository.save(workspace);
        return modelMapper.map(savedWorkspace, WorkspaceResponseDto.class);
    }

    @Override
    public List<WorkspaceResponseDto> getWorkspaceByUser(Long id) {
        Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")){
            throw new RuntimeException("User is not authenticated");
        }
        User user = (User) authentication.getPrincipal();
        List<Workspace> workspaces = workspaceRepository.findByUserId(id);
        return workspaces.stream().map(workspace -> {
            return modelMapper.map(workspace, WorkspaceResponseDto.class);
        }).toList();
    }

    @Override
    public SingleWorkspaceWithProjectsResponseDto getWorkspaceById(Long id) {
        Workspace workspace = workspaceRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Workspace not found."));
        SingleWorkspaceWithProjectsResponseDto  res = modelMapper.map(workspace, SingleWorkspaceWithProjectsResponseDto.class);
        List<ProjectShortResponseDto> projectDto = workspace.getProjects()
                .stream()
                .map(project -> modelMapper.map(project, ProjectShortResponseDto.class))
                .toList();
        res.setProjects(projectDto);
        return res;
    }

    @Override
    public WorkspaceResponseDto updateWorkspace(Long id, WorkspaceRequestDto workspaceRequestDto) {
        Workspace workspace = workspaceRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Workspace not found."));

        workspace.setName(workspaceRequestDto.getName());
        Workspace updatedWorkspace = workspaceRepository.save(workspace);
        return modelMapper.map(updatedWorkspace , WorkspaceResponseDto.class);
    }

    @Override
    public void DeleteWorkspace(Long id) {
        Workspace workspace = workspaceRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Workspace not found."));

        workspaceRepository.deleteById(id);
    }
}
