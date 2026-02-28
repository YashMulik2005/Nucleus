package com.example.Nucleus.service;

import com.example.Nucleus.dto.requestDto.taskRequestDtos.TaskRequestDto;
import com.example.Nucleus.dto.requestDto.taskRequestDtos.UpdateTaskDatesRequestDto;
import com.example.Nucleus.dto.requestDto.taskRequestDtos.UpdateTaskPriorityRequestDto;
import com.example.Nucleus.dto.requestDto.taskRequestDtos.UpdateTaskStatusRequestDto;
import com.example.Nucleus.dto.responseDTO.taskResponseDtos.TaskResponseWithUserDto;
import com.example.Nucleus.dto.responseDTO.taskResponseDtos.TaskResponseWithoutUser;
import com.example.Nucleus.dto.responseDTO.taskResponseDtos.TaskShortResponseWithUserDto;
import com.example.Nucleus.dto.responseDTO.AuthResponseDtos.UserShortResponseDto;
import com.example.Nucleus.dto.responseDTO.taskResponseDtos.TaskSingleDetailedResponseDto;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Repository
public interface TaskService {
    List<String> getValidStatus();
    List<String> getValidPriority();
    TaskResponseWithUserDto addTask(TaskRequestDto taskRequestDto, MultipartFile img);
    TaskResponseWithoutUser updateStatus(Long id, UpdateTaskStatusRequestDto updateTaskStatusRequestDto);
    TaskResponseWithoutUser updatePriority(Long id, UpdateTaskPriorityRequestDto updateTaskPriorityRequestDto);
    TaskResponseWithoutUser updateDates(Long id, UpdateTaskDatesRequestDto updateTaskDatesRequestDto);
    List<UserShortResponseDto> assignUser(Long taskId, Long userId);
    List<UserShortResponseDto> removeUser(Long taskId, Long userId);
    TaskResponseWithUserDto updateTask(Long id, TaskRequestDto taskRequestDto);
    TaskSingleDetailedResponseDto getTaskById(Long id);
    List<TaskShortResponseWithUserDto> getTaskByProject(Long id);
    void deleteTask(Long id);
}
