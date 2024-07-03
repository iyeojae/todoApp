package io.thesun4sky.todoapp.controller;

import io.thesun4sky.todoapp.dto.*;
import io.thesun4sky.todoapp.entity.Comment;
import io.thesun4sky.todoapp.entity.Todo;
import io.thesun4sky.todoapp.security.UserDetailsImpl;
import io.thesun4sky.todoapp.service.CommentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo/{todoId}/comments")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommonResponseDto<CommentResponseDto>> createComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId, @Valid @RequestBody CommentRequestDto requestDto) {
        CommentResponseDto responseDto = commentService.createComment(userDetails, postId, requestDto);

        CommonResponseDto<CommentResponseDto> responseMessage = CommonResponseDto.<CommentResponseDto>builder()
                .statusCode(HttpStatus.CREATED.value())
                .msg("댓글 등록이 완료되었습니다.")
                .data(responseDto)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);

    }

    @GetMapping
    public ResponseEntity<CommonResponseDto<List<CommentResponseDto>>> getAllComments(@PathVariable Long todoId) {
        List<CommentResponseDto> responseDtoList = commentService.getAllComment(todoId);

        CommonResponseDto<List<CommentResponseDto>> responseMessage = CommonResponseDto.<List<CommentResponseDto>>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("전체 댓글 조회가 완료되었습니다.")
                .data(responseDtoList)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommonResponseDto<CommentResponseDto>> updateComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId, @PathVariable Long commentId, @Valid @RequestBody CommentRequestDto requestDto) {
        CommentResponseDto responseDto = commentService.updateComment(userDetails, postId, commentId, requestDto);

        CommonResponseDto<CommentResponseDto> responseMessage = CommonResponseDto.<CommentResponseDto>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("댓글 수정이 완료되었습니다.")
                .data(responseDto)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<CommonResponseDto<Long>> deleteComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId, @PathVariable Long commentId) {
        commentService.deleteComment(userDetails, postId, commentId);

        CommonResponseDto<Long> responseMessage = CommonResponseDto.<Long>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("댓글 삭제가 완료되었습니다.")
                .data(commentId)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
    }
}
