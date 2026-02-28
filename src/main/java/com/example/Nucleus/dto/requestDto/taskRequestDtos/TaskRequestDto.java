package com.example.Nucleus.dto.requestDto.taskRequestDtos;

import com.example.Nucleus.model.enums.PriorityType;
import com.example.Nucleus.model.enums.TaskStatusType;

import java.time.LocalDateTime;
import java.util.List;

public class TaskRequestDto {
    private String title;
    private String description;
    private TaskStatusType status;
    private PriorityType priority;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String img;
    private Long project_id;

    public List<Long> getAssignTo() {
        return assignTo;
    }

    public void setAssignTo(List<Long> assignTo) {
        this.assignTo = assignTo;
    }

    private List<Long> assignTo;

    public TaskRequestDto() {
    }

    public TaskRequestDto(String title, String description, TaskStatusType status, PriorityType priority, LocalDateTime startDate, LocalDateTime endDate, String img, Long project_id, List<Long> assignTo) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;
        this.img = img;
        this.project_id = project_id;
        this.assignTo = assignTo;
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

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    @Override
    public String toString() {
        return "TaskRequestDto{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", priority=" + priority +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", img='" + img + '\'' +
                ", project_id=" + project_id +
                ", assignTo=" + assignTo +
                '}';
    }
}
