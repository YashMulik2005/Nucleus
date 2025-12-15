package com.example.Nucleus.service.impl;

import com.example.Nucleus.dto.requestDto.taskRequestDtos.TaskRequestDto;
import com.example.Nucleus.dto.requestDto.taskRequestDtos.UpdateTaskDatesRequestDto;
import com.example.Nucleus.dto.requestDto.taskRequestDtos.UpdateTaskPriorityRequestDto;
import com.example.Nucleus.dto.requestDto.taskRequestDtos.UpdateTaskStatusRequestDto;
import com.example.Nucleus.dto.responseDTO.taskResponseDtos.TaskResponseWithUserDto;
import com.example.Nucleus.dto.responseDTO.taskResponseDtos.TaskResponseWithoutUser;
import com.example.Nucleus.dto.responseDTO.taskResponseDtos.TaskShortResponseWithUserDto;
import com.example.Nucleus.dto.responseDTO.AuthResponseDtos.UserShortResponseDto;
import com.example.Nucleus.dto.responseDTO.taskResponseDtos.TaskSingleDetailedResponseDto;
import com.example.Nucleus.exception.NotFoundException;
import com.example.Nucleus.model.Activity;
import com.example.Nucleus.model.Project;
import com.example.Nucleus.model.Task;
import com.example.Nucleus.model.User;
import com.example.Nucleus.model.enums.PriorityType;
import com.example.Nucleus.model.enums.TaskStatusType;
import com.example.Nucleus.repository.ProjectRepository;
import com.example.Nucleus.repository.TaskRepository;
import com.example.Nucleus.repository.UserRepository;
import com.example.Nucleus.service.TaskService;
import com.example.Nucleus.utils.GetAuthenticatedUser;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Autowired
    private ActivityServiceImpl activityServiceImpl;

    @Autowired
    private GetAuthenticatedUser getAuthenticatedUser;

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
        Activity activity = new Activity();
        activity.setMessage("task added.");
        activity.setTask(savedTask);
        activity.setUser(user);
        activityServiceImpl.addActivity(activity);
        return modelMapper.map(savedTask, TaskResponseWithUserDto.class);
    }

    @Override
    @Transactional
    public TaskResponseWithoutUser updateStatus(Long id, UpdateTaskStatusRequestDto updateTaskStatusRequestDto) {
        User user = getAuthenticatedUser.getAuthenticatedUserDetails();
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Task not found."));
        TaskStatusType temp = task.getStatus();
        task.setStatus(updateTaskStatusRequestDto.getStatus());
        Task updatedTask = taskRepository.save(task);

        Task savedTask = taskRepository.save(task);

        Activity activity = new Activity();
        activity.setMessage("task status updated from "+ temp +" to " + savedTask.getStatus());
        activity.setTask(savedTask);
        activity.setUser(user);
        activityServiceImpl.addActivity(activity);

        return modelMapper.map(updatedTask, TaskResponseWithoutUser.class);
    }

    @Override
    @Transactional
    public TaskResponseWithoutUser updatePriority(Long id, UpdateTaskPriorityRequestDto updateTaskPriorityRequestDto) {
        User user = getAuthenticatedUser.getAuthenticatedUserDetails();
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Task not found."));
        PriorityType temp = task.getPriority();
        task.setPriority(updateTaskPriorityRequestDto.getPriority());
        Task updatedTask = taskRepository.save(task);

        Activity activity = new Activity();
        activity.setMessage("task priority updated from "+ temp +" to " + updatedTask.getPriority());
        activity.setTask(updatedTask);
        activity.setUser(user);
        activityServiceImpl.addActivity(activity);

        return modelMapper.map(updatedTask, TaskResponseWithoutUser.class);
    }

    @Override
    public TaskResponseWithoutUser updateDates(Long id, UpdateTaskDatesRequestDto updateTaskDatesRequestDto) {
        User user = getAuthenticatedUser.getAuthenticatedUserDetails();
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Task not found."));

        LocalDateTime tempStartDate = task.getStartDate();
        LocalDateTime tempEndDate = task.getEndDate();

        task.setStartDate(updateTaskDatesRequestDto.getStartDate());
        task.setEndDate(updateTaskDatesRequestDto.getEndDate());
        Task updatedTask = taskRepository.save(task);

        Activity activity = new Activity();
        activity.setMessage("task dates updated from start date: "+ tempStartDate +" and end date:  " + tempEndDate
                + " to start date: " + updatedTask.getStartDate() + "and end date: " + updatedTask.getEndDate());
        activity.setTask(updatedTask);
        activity.setUser(user);
        activityServiceImpl.addActivity(activity);

        return modelMapper.map(updatedTask, TaskResponseWithoutUser.class);
    }

    @Override
    @Transactional
    public List<UserShortResponseDto> assignUser(Long taskId, Long userId) {
        User loggedUser = getAuthenticatedUser.getAuthenticatedUserDetails();
        Task task = taskRepository.findById(taskId)
                .orElseThrow(()-> new NotFoundException("Task not found."));
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new NotFoundException("User not found."));

        // check for user joined project - pending
        if(task.getAssignTo().contains(user)){
            throw new RuntimeException("User already assigned to this task.");
        }

        task.getAssignTo().add(user);
        Task updatedTask = taskRepository.save(task);

        Activity activity = new Activity();
        activity.setMessage(user.getDisplayName()+" assigned to task.");
        activity.setTask(updatedTask);
        activity.setUser(loggedUser);
        activityServiceImpl.addActivity(activity);

        return updatedTask.getAssignTo().stream()
                .map(assignUser -> modelMapper.map(assignUser, UserShortResponseDto.class))
                .toList();
    }

    @Override
    @Transactional
    public List<UserShortResponseDto> removeUser(Long taskId, Long userId) {
        User loggedUser = getAuthenticatedUser.getAuthenticatedUserDetails();
        Task task = taskRepository.findById(taskId)
                .orElseThrow(()-> new NotFoundException("Task not found."));
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new NotFoundException("User not found."));

        if(!task.getAssignTo().contains(user)){
            throw new RuntimeException("User is not assigned to this task.");
        }

        task.getAssignTo().remove(user);
        Task updatedTask = taskRepository.save(task);

        Activity activity = new Activity();
        activity.setMessage(user.getDisplayName()+" removed from task.");
        activity.setTask(updatedTask);
        activity.setUser(loggedUser);
        activityServiceImpl.addActivity(activity);

        return updatedTask.getAssignTo().stream()
                .map(assignUser -> modelMapper.map(assignUser, UserShortResponseDto.class))
                .toList();
    }

    @Override
    public TaskResponseWithUserDto updateTask(Long id, TaskRequestDto taskRequestDto) {
        User loggedUser = getAuthenticatedUser.getAuthenticatedUserDetails();
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

        Activity activity = new Activity();
        activity.setMessage("Task updated by "+loggedUser.getDisplayName());
        activity.setTask(updatedTask);
        activity.setUser(loggedUser);
        activityServiceImpl.addActivity(activity);

        return modelMapper.map(updatedTask, TaskResponseWithUserDto.class);
    }

    @Override
    public TaskSingleDetailedResponseDto getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Task not found."));

        return modelMapper.map(task, TaskSingleDetailedResponseDto.class);
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
