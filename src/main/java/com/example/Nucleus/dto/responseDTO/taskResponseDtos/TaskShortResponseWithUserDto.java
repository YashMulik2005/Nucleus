package com.example.Nucleus.dto.responseDTO.taskResponseDtos;

import com.example.Nucleus.dto.responseDTO.AuthResponseDtos.UserShortResponseDto;
import com.example.Nucleus.model.enums.PriorityType;
import com.example.Nucleus.model.enums.TaskStatusType;

import java.time.LocalDateTime;
import java.util.List;

public class TaskShortResponseWithUserDto {
    private String title;
    private TaskStatusType status;
    private PriorityType priority;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    List<UserShortResponseDto> assignTo;

    public TaskShortResponseWithUserDto() {
    }

    public TaskShortResponseWithUserDto(String title, TaskStatusType status, PriorityType priority, LocalDateTime startDate, LocalDateTime endDate, List<UserShortResponseDto> assignTo) {
        this.title = title;
        this.status = status;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;
        this.assignTo = assignTo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TaskStatusType getStatus() {
        return status;
    }

    public void setStatus(TaskStatusType status) {
        this.status = status;
    }

    public PriorityType getPriority() {
        return priority;
    }

    public void setPriority(PriorityType priority) {
        this.priority = priority;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public List<UserShortResponseDto> getAssignTo() {
        return assignTo;
    }

    public void setAssignTo(List<UserShortResponseDto> assignTo) {
        this.assignTo = assignTo;
    }
}
