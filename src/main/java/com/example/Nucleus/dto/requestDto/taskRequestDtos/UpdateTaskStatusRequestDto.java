package com.example.Nucleus.dto.requestDto.taskRequestDtos;

import com.example.Nucleus.model.enums.TaskStatusType;
import jakarta.validation.constraints.NotBlank;

public class UpdateTaskStatusRequestDto {
    @NotBlank(message = "Status is required.")
    private TaskStatusType status;

    public UpdateTaskStatusRequestDto() {
    }

    public UpdateTaskStatusRequestDto(TaskStatusType status) {
        this.status = status;
    }

    public @NotBlank(message = "Status is required.") TaskStatusType getStatus() {
        return status;
    }

    public void setStatus(@NotBlank(message = "Status is required.") TaskStatusType status) {
        this.status = status;
    }
}
