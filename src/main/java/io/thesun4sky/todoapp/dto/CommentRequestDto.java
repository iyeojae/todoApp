package io.thesun4sky.todoapp.dto;

import io.thesun4sky.todoapp.entity.Comment;
import io.thesun4sky.todoapp.entity.Todo;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    @NotBlank(message = "댓글 내용을 입력해주세요.")
    private String contents;

    public Comment toEntity() {
        return Comment.builder()
                .contents(contents)
                .build();
    }
}
