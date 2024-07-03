package io.thesun4sky.todoapp.dto;

import io.thesun4sky.todoapp.entity.Comment;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponseDto {
    private String userId;
    private Long postId;
    private String contents;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment) {
        this.userId = comment.getUser().getUserId();
        this.postId = comment.getTodo().getTodoId();
        this.contents = comment.getContents();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
