package com.example.Nucleus.service.impl;

import com.example.Nucleus.dto.requestDto.projectRequestDtos.ProjectRequestDto;
import com.example.Nucleus.dto.responseDTO.projectResponseDtos.ProjectJoinCodeResponse;
import com.example.Nucleus.dto.responseDTO.projectResponseDtos.ProjectResponseDto;
import com.example.Nucleus.dto.responseDTO.AuthResponseDtos.UserShortResponseDto;
import com.example.Nucleus.exception.NotFoundException;
import com.example.Nucleus.model.Project;
import com.example.Nucleus.model.ProjectJoinCode;
import com.example.Nucleus.model.User;
import com.example.Nucleus.model.Workspace;
import com.example.Nucleus.repository.ProjectJoinCodeRepository;
import com.example.Nucleus.repository.ProjectRepository;
import com.example.Nucleus.repository.WorkspaceRepository;
import com.example.Nucleus.service.ProjectService;
import com.example.Nucleus.utils.RandomCodeGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private WorkspaceRepository workspaceRepository;

    @Autowired
    private ProjectJoinCodeRepository projectJoinCodeRepository;

    @Autowired
    private RandomCodeGenerator randomCodeGenerator;

    @Override
    public ProjectResponseDto addProject(ProjectRequestDto projectRequestDto) {
        Workspace workspace = workspaceRepository.findById(projectRequestDto.getWorkspaceId())
                .orElseThrow(()-> new NotFoundException("Workspace not found."));

        Project project = new Project();
        project.setName(projectRequestDto.getName());
        project.setDescription(projectRequestDto.getDescription());
        project.setWorkspace(workspace);
        System.out.println(project.toString());

        Project savedProject = projectRepository.save(project);
        return modelMapper.map(savedProject, ProjectResponseDto.class);
    }

    @Override
    public ProjectJoinCodeResponse generateProjectCode(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Project not found."));

        String code = randomCodeGenerator.generateCode(6);
        ProjectJoinCode projectJoinCode = new ProjectJoinCode();
        projectJoinCode.setCode(code);
        projectJoinCode.setExpireTime(LocalDateTime.now().plusMinutes(30));
        projectJoinCode.setProject(project);
        ProjectJoinCode savedProjectJoinCode =projectJoinCodeRepository.save(projectJoinCode);
        return modelMapper.map(savedProjectJoinCode, ProjectJoinCodeResponse.class);
    }

    @Override
    public ProjectResponseDto joinProject(String code) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymousUser")){
            throw new RuntimeException("User is not authenticated.");
        }
        User user = (User) authentication.getPrincipal();
        ProjectJoinCode projectCode = projectJoinCodeRepository.findByCode(code)
                .orElseThrow(()-> new NotFoundException("Project code is invalid."));

        if(projectCode.getExpireTime().isBefore(LocalDateTime.now())){
            throw new RuntimeException("Project code has expired.");
        }

        Project project = projectCode.getProject();

        if(project.getUsers().contains(user)){
            throw new RuntimeException("User already part of this project.");
        }

        project.getUsers().add(user);
        Project savedProject = projectRepository.save(project);

        return modelMapper.map(savedProject, ProjectResponseDto.class);
    }

    @Override
    public List<UserShortResponseDto> getProjectUsers(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Project not found."));

        List<UserShortResponseDto> res = project.getUsers()
                .stream()
                .map(user -> modelMapper.map(user, UserShortResponseDto.class))
                .toList();

        return res;
    }

    @Override
    public ProjectResponseDto UpdateProject(Long id, ProjectRequestDto projectRequestDto) {
        Project project = projectRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Project not found."));

        project.setName(projectRequestDto.getName());
        project.setDescription(projectRequestDto.getDescription());
        Project updatedProject = projectRepository.save(project);
        return modelMapper.map(updatedProject, ProjectResponseDto.class);
    }

    @Override
    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Project not found."));

        projectRepository.deleteById(id);
    }


}
