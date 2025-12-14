package com.example.Nucleus.service.impl;

import com.example.Nucleus.dto.requestDto.commentRequestDtos.CommentRequestDto;
import com.example.Nucleus.dto.requestDto.commentRequestDtos.UpdateCommentRequestDto;
import com.example.Nucleus.dto.responseDTO.commentResponseDtos.CommentResponseDto;
import com.example.Nucleus.exception.NotFoundException;
import com.example.Nucleus.model.Comment;
import com.example.Nucleus.model.Task;
import com.example.Nucleus.model.User;
import com.example.Nucleus.repository.CommentRepository;
import com.example.Nucleus.repository.TaskRepository;
import com.example.Nucleus.repository.UserRepository;
import com.example.Nucleus.service.CommentService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public CommentResponseDto addComment(CommentRequestDto commentRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated() ||
                authentication.getPrincipal().equals("anonymeousUser")){
            throw new RuntimeException("Unauthenticated.");
        }
        User user = (User) authentication.getPrincipal();
        Task task = taskRepository.findById(commentRequestDto.getTaskId())
                .orElseThrow(()-> new NotFoundException("Task not found."));
        Comment comment = modelMapper.map(commentRequestDto, Comment.class);
        comment.setId(null);
        comment.setUser(user);
        comment.setTask(task);
        Comment savedComment = commentRepository.save(comment);
        return modelMapper.map(savedComment, CommentResponseDto.class);
    }

    @Override
    public CommentResponseDto updateComment(Long id, UpdateCommentRequestDto updateCommentRequestDto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Comment not found."));

        comment.setMessage(updateCommentRequestDto.getMessage());
        Comment savedComment = commentRepository.save(comment);
        return modelMapper.map(savedComment, CommentResponseDto.class);
    }

    @Override
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Comment not found."));

        commentRepository.delete(comment);
    }

    @Override
    public List<CommentResponseDto> getCommentsByTaskId(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(()-> new NotFoundException("Task not not found;"));

        List<Comment> comments = commentRepository.findByTaskId(taskId);
        return comments.stream()
                .map(comment -> modelMapper.map(comment, CommentResponseDto.class))
                .toList();
    }

    @Override
    public List<CommentResponseDto> getCommentsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new NotFoundException("User not found."));

        List<Comment> comments = commentRepository.findByUserId(userId);
        return comments.stream()
                .map(comment -> modelMapper.map(comment, CommentResponseDto.class))
                .toList();
    }
}
