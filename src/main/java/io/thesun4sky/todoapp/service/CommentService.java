package io.thesun4sky.todoapp.service;

import io.thesun4sky.todoapp.dto.CommentRequestDto;
import io.thesun4sky.todoapp.dto.CommentResponseDto;
import io.thesun4sky.todoapp.entity.Comment;
import io.thesun4sky.todoapp.entity.Todo;
import io.thesun4sky.todoapp.exception.CommentException;
import io.thesun4sky.todoapp.exception.NoContentException;
import io.thesun4sky.todoapp.repository.CommentRepository;
import io.thesun4sky.todoapp.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository repository;
    private final TodoService todoService;
    @Transactional
    public CommentResponseDto createComment(UserDetailsImpl userDetails, Long todoId, CommentRequestDto requestDto) {
        Todo todo = todoService.getValidateTodo(todoId);
        Comment comment = Comment.builder()
                .todo(todo)
                .user(userDetails.getUser())
                .contents(requestDto.getContents())
                .build();

        repository.save(comment);
        return new CommentResponseDto(comment);
    }

    public List<CommentResponseDto> getAllComment(Long todoId) {
        Todo todo = todoService.getValidateTodo(todoId);
        List<Comment> commentList = todo.getCommentList();

        if (commentList.isEmpty()) {
            throw new NoContentException("가장 먼저 댓글을 작성해보세요!");
        }

        return commentList.stream().map(CommentResponseDto::new).toList();
    }

    public CommentResponseDto getComment(Long todoId, Long commentId) {
        Todo todo = todoService.getValidateTodo(todoId);

        Comment comment = getValidateComment(todo.getId(), commentId);

        return new CommentResponseDto(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(UserDetailsImpl userDetails, Long todoId, Long commentId, CommentRequestDto requestDto) {
        Todo todo = todoService.getValidateTodo(todoId);
        Comment comment = getValidateComment(todo.getId(), commentId);
        checkCommentWriter(comment, userDetails);

        comment.update(requestDto);
        repository.save(comment);

        return new CommentResponseDto(comment);
    }

    @Transactional
    public void deleteComment(UserDetailsImpl userDetails, Long todoId, Long commentId) {
        Todo todo = todoService.getValidateTodo(todoId);
        Comment comment = getValidateComment(todo.getId(), commentId);
        checkCommentWriter(comment, userDetails);

        repository.delete(comment);
    }

    public Comment getValidateComment(Long todoId, Long commentId) {
        return repository.findByTodoIdAndId(todoId, commentId).orElseThrow(() ->
                new CommentException("게시글에 해당 댓글이 존재하지 않습니다."));
    }

    private void checkCommentWriter(Comment comment, UserDetailsImpl userDetails) {
        if (!comment.getUser().getId().equals(userDetails.getUsername())) {
            throw new CommentException("작성자가 아니므로, 접근이 제한됩니다.");
        }
    }
}
