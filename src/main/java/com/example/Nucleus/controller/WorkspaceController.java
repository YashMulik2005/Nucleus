package com.example.Nucleus.controller;

import com.example.Nucleus.dto.SucessResponseHandler;
import com.example.Nucleus.dto.requestDto.workspaceRequestDtos.WorkspaceRequestDto;
import com.example.Nucleus.service.impl.WorkspaceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/workspace")
public class WorkspaceController {

    @Autowired
    private WorkspaceServiceImpl workspaceServiceImpl;

    @PostMapping("/add")
    public ResponseEntity<?> addWorkspace(@RequestBody WorkspaceRequestDto workspaceRequestDto){
        System.out.println("in api");
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.CREATED, true,
                "Workspace created sucessfully.", workspaceServiceImpl.addWorkspace(workspaceRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWorkspaceById(@PathVariable Long id){
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK, true,
                "Workspace fetched successfully.", workspaceServiceImpl.getWorkspaceById(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getWorkspaceByUser(@PathVariable Long id){
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK, true,
                "Workspaces fetched successfully.", workspaceServiceImpl.getWorkspaceByUser(id));
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> updateWorkspace(@PathVariable Long id, @RequestBody WorkspaceRequestDto workspaceRequestDto){
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK, true,
                "Workspace updated successfully.", workspaceServiceImpl.updateWorkspace(id, workspaceRequestDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteWorkspace(@PathVariable Long id){
        workspaceServiceImpl.DeleteWorkspace(id);
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK, true,
                "Workspace deleted successfully.", null);
    }
}
