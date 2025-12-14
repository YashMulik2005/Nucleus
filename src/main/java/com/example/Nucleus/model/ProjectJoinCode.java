package com.example.Nucleus.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ProjectJoinCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private LocalDateTime expireTime;
    @ManyToOne()
    private Project project;

    public ProjectJoinCode() {
    }

    public ProjectJoinCode(Long id, String code, LocalDateTime expireTime, Project project) {
        this.id = id;
        this.code = code;
        this.expireTime = expireTime;
        this.project = project;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return "ProjectJoinCode{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", expireTime=" + expireTime +
                ", project=" + project +
                '}';
    }
}
