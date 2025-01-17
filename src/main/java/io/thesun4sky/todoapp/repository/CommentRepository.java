package io.thesun4sky.todoapp.repository;

import io.thesun4sky.todoapp.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Optional<Comment> findByTodoIdAndId(Long todoId, Long commentId);
}
