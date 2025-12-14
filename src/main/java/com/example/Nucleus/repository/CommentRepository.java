package com.example.Nucleus.repository;

import com.example.Nucleus.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Override
    Optional<Comment> findById(Long aLong);
    List<Comment> findByTaskId(Long id);
    List<Comment>findByUserId(Long id);
}
