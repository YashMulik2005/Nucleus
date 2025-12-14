package com.example.Nucleus.service;

import com.example.Nucleus.dto.requestDto.commentRequestDtos.CommentRequestDto;
import com.example.Nucleus.dto.requestDto.commentRequestDtos.UpdateCommentRequestDto;
import com.example.Nucleus.dto.responseDTO.commentResponseDtos.CommentResponseDto;

import java.util.List;

public interface CommentService {
    CommentResponseDto addComment(CommentRequestDto commentRequestDto);
    CommentResponseDto updateComment(Long id,UpdateCommentRequestDto updateCommentRequestDto);
    void deleteComment(Long id);
    List<CommentResponseDto> getCommentsByTaskId(Long taskId);
    List<CommentResponseDto> getCommentsByUserId(Long userId);
}
