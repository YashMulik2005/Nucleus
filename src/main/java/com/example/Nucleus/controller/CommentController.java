package com.example.Nucleus.controller;

import com.example.Nucleus.dto.SucessResponseHandler;
import com.example.Nucleus.dto.requestDto.commentRequestDtos.CommentRequestDto;
import com.example.Nucleus.dto.requestDto.commentRequestDtos.UpdateCommentRequestDto;
import com.example.Nucleus.service.impl.CommentServiceImpl;
import com.example.Nucleus.service.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentServiceImpl commentServiceImpl;

    @PostMapping("/add")
    public ResponseEntity<Object> addComment(@RequestBody CommentRequestDto commentRequestDto){
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK, true,
                "Comment added successfully.", commentServiceImpl.addComment(commentRequestDto));
    }

    @GetMapping("/getByTask/{taskId}")
    public ResponseEntity<Object> getCommentByTask(@PathVariable Long taskId){
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK, true,
                "Comments fetched Successfully.", commentServiceImpl.getCommentsByTaskId(taskId));
    }

    @GetMapping("/getByUser/{userId}")
    public ResponseEntity<Object> getCommentByUser(@PathVariable Long userId){
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK, true,
                "Comments fetched Successfully.", commentServiceImpl.getCommentsByUserId(userId));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteComment(@PathVariable Long id){
        commentServiceImpl.deleteComment(id);
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK, true,
                "Comment deleted Successfully.", null);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Object> updateComment(@PathVariable Long id, @RequestBody UpdateCommentRequestDto updateCommentRequestDto){
        return SucessResponseHandler.SucessResponseBuilder(HttpStatus.OK, true,
                "Comment updated Successfully.", commentServiceImpl.updateComment(id, updateCommentRequestDto));
    }
}
