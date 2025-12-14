package com.example.Nucleus.dto.requestDto.projectRequestDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class JoinProjectDto {
    @NotBlank(message = "Token is required.")
    @Size(max = 6 , message = "Token must of size 6.")
    private String code;

    public JoinProjectDto() {
    }

    public JoinProjectDto(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
