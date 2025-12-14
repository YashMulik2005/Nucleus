package com.example.Nucleus.dto.responseDTO.taskResponseDtos;

import com.example.Nucleus.model.enums.PriorityType;
import com.example.Nucleus.model.enums.TaskStatusType;

import java.time.LocalDateTime;

public class TaskResponseWithoutUser {
    private String title;
    private String description;
    private TaskStatusType status;
    private PriorityType priority;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String img;

    public TaskResponseWithoutUser() {
    }

    public TaskResponseWithoutUser(String title, String description, TaskStatusType status, PriorityType priority, LocalDateTime startDate, LocalDateTime endDate, String img) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
