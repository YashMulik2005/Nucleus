package com.example.Nucleus.dto.responseDTO.projectResponseDtos;

import java.time.LocalDateTime;

public class ProjectJoinCodeResponse {
            private String joinCode;
            private LocalDateTime expireTime;

            public ProjectJoinCodeResponse() {
            }
            public ProjectJoinCodeResponse(String joinCode, LocalDateTime expireAt) {
                this.joinCode = joinCode;
                this.expireTime = expireAt;
            }

    public String getJoinCode() {
        return joinCode;
    }

    public void setJoinCode(String joinCode) {
        this.joinCode = joinCode;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public String toString() {
        return "ProjectJoinCodeResponse{" +
                "joinCode='" + joinCode + '\'' +
                ", expireAt=" + expireTime +
                '}';
    }
}
