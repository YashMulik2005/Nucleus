package com.example.Nucleus.repository;

import com.example.Nucleus.model.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
    List<Workspace> findByUserId(Long Long);
    Optional<Workspace> findById(Long id);
}
