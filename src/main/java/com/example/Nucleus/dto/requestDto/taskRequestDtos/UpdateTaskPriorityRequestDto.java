package com.example.Nucleus.dto.requestDto.taskRequestDtos;

import com.example.Nucleus.model.enums.PriorityType;

public class UpdateTaskPriorityRequestDto {
    private PriorityType priority;

    public UpdateTaskPriorityRequestDto() {
    }

    public UpdateTaskPriorityRequestDto(PriorityType priority) {
        this.priority = priority;
    }

    public PriorityType getPriority() {
        return priority;
    }

    public void setPriority(PriorityType priority) {
        this.priority = priority;
    }
}
