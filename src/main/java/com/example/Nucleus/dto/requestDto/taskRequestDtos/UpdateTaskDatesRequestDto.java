package com.example.Nucleus.dto.requestDto.taskRequestDtos;

import java.time.LocalDateTime;

public class UpdateTaskDatesRequestDto {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public UpdateTaskDatesRequestDto() {
    }

    public UpdateTaskDatesRequestDto(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
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
}
