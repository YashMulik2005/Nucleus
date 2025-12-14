package com.example.Nucleus.service.impl;

import com.example.Nucleus.dto.requestDto.taskRequestDtos.TaskRequestDto;
import com.example.Nucleus.dto.requestDto.taskRequestDtos.UpdateTaskDatesRequestDto;
import com.example.Nucleus.dto.requestDto.taskRequestDtos.UpdateTaskPriorityRequestDto;
import com.example.Nucleus.dto.requestDto.taskRequestDtos.UpdateTaskStatusRequestDto;
import com.example.Nucleus.dto.responseDTO.taskResponseDtos.TaskResponseWithUserDto;
import com.example.Nucleus.dto.responseDTO.taskResponseDtos.TaskResponseWithoutUser;
import com.example.Nucleus.dto.responseDTO.taskResponseDtos.TaskShortResponseWithUserDto;
import com.example.Nucleus.dto.responseDTO.AuthResponseDtos.UserShortResponseDto;
import com.example.Nucleus.exception.NotFoundException;
import com.example.Nucleus.model.Project;
import com.example.Nucleus.model.Task;
import com.example.Nucleus.model.User;
import com.example.Nucleus.model.enums.PriorityType;
import com.example.Nucleus.model.enums.TaskStatusType;
import com.example.Nucleus.repository.ProjectRepository;
import com.example.Nucleus.repository.TaskRepository;
import com.example.Nucleus.repository.UserRepository;
import com.example.Nucleus.service.TaskService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<String> getValidStatus() {
        return Arrays.stream(TaskStatusType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getValidPriority() {
        return Arrays.stream(PriorityType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TaskResponseWithUserDto addTask(TaskRequestDto taskRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !authentication.isAuthenticated() || authentication.getPrincipal().equals("anonymeousUser")){
            throw new RuntimeException("Unauthenticated.");
        }
        User user = (User) authentication.getPrincipal();
        Project project = projectRepository.findById(taskRequestDto.getProject_id())
                .orElseThrow(()-> new NotFoundException("Project not found."));

        Task task = modelMapper.map(taskRequestDto, Task.class);
        task.setCreatedBy(user);
        task.setProject(project);

        if(taskRequestDto.getAssignTo() != null && !taskRequestDto.getAssignTo().isEmpty()){
            List<User> assigness = taskRequestDto.getAssignTo().stream()
                    .map(userId -> userRepository.findById(userId)
                            .orElseThrow(()-> new NotFoundException("User not found.")))
                    .toList();

            task.getAssignTo().addAll(assigness);
        }
        Task savedTask = taskRepository.save(task);
        return modelMapper.map(savedTask, TaskResponseWithUserDto.class);
    }

    @Override
    public TaskResponseWithoutUser updateStatus(Long id, UpdateTaskStatusRequestDto updateTaskStatusRequestDto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Task not found."));

        task.setStatus(updateTaskStatusRequestDto.getStatus());
        Task updatedTask = taskRepository.save(task);

        return modelMapper.map(updatedTask, TaskResponseWithoutUser.class);
    }

    @Override
    public TaskResponseWithoutUser updatePriority(Long id, UpdateTaskPriorityRequestDto updateTaskPriorityRequestDto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Task not found."));

        task.setPriority(updateTaskPriorityRequestDto.getPriority());
        Task updatedTask = taskRepository.save(task);

        return modelMapper.map(updatedTask, TaskResponseWithoutUser.class);
    }

    @Override
    public TaskResponseWithoutUser updateDates(Long id, UpdateTaskDatesRequestDto updateTaskDatesRequestDto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Task not found."));

        task.setStartDate(updateTaskDatesRequestDto.getStartDate());
        task.setEndDate(updateTaskDatesRequestDto.getEndDate());
        Task updatedTask = taskRepository.save(task);

        return modelMapper.map(updatedTask, TaskResponseWithoutUser.class);
    }

    @Override
    @Transactional
    public List<UserShortResponseDto> assignUser(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(()-> new NotFoundException("Task not found."));
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new NotFoundException("User not found."));

        if(task.getAssignTo().contains(user)){
            throw new RuntimeException("User already assigned to this task.");
        }

        task.getAssignTo().add(user);
        Task updatedTask = taskRepository.save(task);
        return updatedTask.getAssignTo().stream()
                .map(assignUser -> modelMapper.map(assignUser, UserShortResponseDto.class))
                .toList();
    }

    @Override
    @Transactional
    public List<UserShortResponseDto> removeUser(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(()-> new NotFoundException("Task not found."));
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new NotFoundException("User not found."));

        if(!task.getAssignTo().contains(user)){
            throw new RuntimeException("User is not assigned to this task.");
        }

        task.getAssignTo().remove(user);
        Task updatedTask = taskRepository.save(task);
        return updatedTask.getAssignTo().stream()
                .map(assignUser -> modelMapper.map(assignUser, UserShortResponseDto.class))
                .toList();
    }

    @Override
    public TaskResponseWithUserDto updateTask(Long id, TaskRequestDto taskRequestDto) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found."));

        task.setTitle(taskRequestDto.getTitle());
        task.setDescription(taskRequestDto.getDescription());
        task.setStatus(taskRequestDto.getStatus());
        task.setPriority(taskRequestDto.getPriority());
        task.setStartDate(taskRequestDto.getStartDate());
        task.setEndDate(taskRequestDto.getEndDate());
        task.setImg(taskRequestDto.getImg());

        Task updatedTask = taskRepository.save(task);

        return modelMapper.map(updatedTask, TaskResponseWithUserDto.class);
    }

    @Override
    public TaskResponseWithUserDto getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Task not found."));

        return modelMapper.map(task, TaskResponseWithUserDto.class);
    }

    @Override
    public List<TaskShortResponseWithUserDto> getTaskByProject(Long id) {
        List<Task> tasks = taskRepository.findByProjectId(id)
                .orElseThrow(()-> new NotFoundException("Project not found."));

        List<TaskShortResponseWithUserDto> res = tasks.stream().map(task -> modelMapper.map(task, TaskShortResponseWithUserDto.class))
                .toList();
        return res;
    }

    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Task not found."));

        taskRepository.delete(task);
    }
}
