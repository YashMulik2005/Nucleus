package com.example.Nucleus.controller;

import com.example.Nucleus.dto.SucessResponseHandler;
import com.example.Nucleus.dto.requestDto.taskRequestDtos.TaskRequestDto;
import com.example.Nucleus.dto.requestDto.taskRequestDtos.UpdateTaskDatesRequestDto;
import com.example.Nucleus.dto.requestDto.taskRequestDtos.UpdateTaskPriorityRequestDto;
import com.example.Nucleus.dto.requestDto.taskRequestDtos.UpdateTaskStatusRequestDto;
import com.example.Nucleus.service.impl.TaskServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskServiceImpl taskServiceImpl;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/status")
    public ResponseEntity<Object> getValidStatus(){
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK, true,
                "Fetched valid status successfully.", taskServiceImpl.getValidStatus());
    }

    @GetMapping("/priority")
    public ResponseEntity<Object> getValidPriority(){
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK, true,
                "Fetched valid status successfully.", taskServiceImpl.getValidPriority());
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> addTask(
            @RequestPart(value = "img", required = false) MultipartFile image,
            @RequestPart(value = "task") String taskData){

        ObjectMapper objectMapper = new ObjectMapper();
        TaskRequestDto taskRequestDto = objectMapper.readValue(taskData, TaskRequestDto.class);

        System.out.println("going to service.");
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK, true,
                "Task added successfully.", taskServiceImpl.addTask(taskRequestDto, image));
    }

    @GetMapping("/get/{taskId}")
    public ResponseEntity<Object> getTaskById(@PathVariable Long taskId){
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK, true,
                "Task data fetched Successfully.", taskServiceImpl.getTaskById(taskId));
    }

    @GetMapping("/taskByProject/{projectId}")
    public ResponseEntity<Object> getTaskByProject(@PathVariable Long projectId){
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK, true,
                "Task fetched successfully.", taskServiceImpl.getTaskByProject(projectId));
    }

    @PatchMapping("/updateStatus/{id}")
    public ResponseEntity<Object> updateStatus(@PathVariable Long id, @RequestBody UpdateTaskStatusRequestDto updateTaskStatusRequestDto){
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK, true,
                "Status updated Successfully.", taskServiceImpl.updateStatus(id,updateTaskStatusRequestDto));
    }


    @PatchMapping("/updatePriority/{id}")
    public ResponseEntity<Object> updatePriority(@PathVariable Long id, @RequestBody UpdateTaskPriorityRequestDto updateTaskPriorityRequestDto){
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK, true,
                "Priority updated Successfully.", taskServiceImpl.updatePriority(id, updateTaskPriorityRequestDto));
    }

    @PatchMapping("/updateDates/{id}")
    public ResponseEntity<Object> updateDates(@PathVariable Long id, @RequestBody UpdateTaskDatesRequestDto updateTaskDatesRequestDto){
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK, true,
                "Dates updated Successfully.", taskServiceImpl.updateDates(id, updateTaskDatesRequestDto));
    }

    @PatchMapping("/{taskId}/assignUser/{userId}")
    public ResponseEntity<Object> AssignUser(@PathVariable Long taskId, @PathVariable Long userId){
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK, true,
                "User assigned Successfully.", taskServiceImpl.assignUser(taskId,userId));
    }

    @PatchMapping("/{taskId}/removeUser/{userId}")
    public ResponseEntity<Object> removeUser(@PathVariable Long taskId, @PathVariable Long userId){
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK, true,
                "User assigned successfully.", taskServiceImpl.removeUser(taskId, userId));
    }

    @PutMapping("/update/{taskId}")
    public ResponseEntity<Object> updateTask(@PathVariable Long taskId, @RequestBody TaskRequestDto taskRequestDto){
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK, true,
                "User assigned successfully.", taskServiceImpl.updateTask(taskId, taskRequestDto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteTask(@PathVariable Long id){
        taskServiceImpl.deleteTask(id);
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK, true,
                "Task deleted successfully.", null);
    }

}
