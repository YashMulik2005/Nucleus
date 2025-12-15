package com.example.Nucleus.controller;

import com.example.Nucleus.dto.SucessResponseHandler;
import com.example.Nucleus.dto.requestDto.projectRequestDtos.JoinProjectDto;
import com.example.Nucleus.dto.requestDto.projectRequestDtos.ProjectRequestDto;
import com.example.Nucleus.service.impl.ProjectServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    private ProjectServiceImpl projectServiceImpl;

    @PostMapping("/add")
    public ResponseEntity<Object> addProject(@Valid @RequestBody ProjectRequestDto projectRequestDto){
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK, true,
                "Project added successfully.", projectServiceImpl.addProject(projectRequestDto));
    }

    @GetMapping("/joinCode/{id}")
    public ResponseEntity<Object> generateJoinCode(@PathVariable Long id){
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK, true,
                "Code generated successfully.", projectServiceImpl.generateProjectCode(id));
    }

    @PostMapping("/joinProject")
    public ResponseEntity<Object> joinProject(@Valid @RequestBody JoinProjectDto joinProjectDto){
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK,true,
                "Join project successfully.",projectServiceImpl.joinProject(joinProjectDto.getCode()));
    }

    @PatchMapping("{projectId}/removeUser/{userId}")
    public ResponseEntity<Object> removeUser(@PathVariable Long projectId, @PathVariable Long userId){
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK,true,
                "User Removed from project successfully.",null);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<Object> usersOfProject(@PathVariable Long id){
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK, true,
                "Users of project fetched sucessfully.", projectServiceImpl.getProjectUsers(id));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> updateProject(@PathVariable Long id , @RequestBody ProjectRequestDto projectRequestDto){
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK, true,
                "Project updated successfully.",projectServiceImpl.UpdateProject(id, projectRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProject(@PathVariable Long id){
        projectServiceImpl.deleteProject(id);
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK, true,
                "Project deleted successfully", null);
    }
}
