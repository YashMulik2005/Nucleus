package com.example.Nucleus.repository;

import com.example.Nucleus.model.ProjectJoinCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectJoinCodeRepository extends JpaRepository<ProjectJoinCode, Long> {
    Optional<ProjectJoinCode> findByCode(String code);
}
