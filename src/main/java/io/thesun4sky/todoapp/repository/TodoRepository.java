package io.thesun4sky.todoapp.repository;

import io.thesun4sky.todoapp.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Page<Todo> findAll(Pageable pageable);

    @Query("select todo from Todo todo " +
            "where todo.createdAt >= :startDate and todo.createdAt < :finishDate")
    Page<Todo> findPostPageByPeriod(@Param("startDate") LocalDateTime startDate, @Param("finishDate") LocalDateTime finishDate, Pageable pageable);

    @Query("select todo from Todo todo " +
            "where todo.createdAt >= :startDate")
    Page<Todo> findPostPageByStartDate(@Param("startDate") LocalDateTime startDate, Pageable pageable);

    @Query("select todo from Todo todo " +
            "where todo.createdAt < :finishDate")
    Page<Todo> findPostPageByFinishDate(@Param("finishDate")LocalDateTime finishDate, Pageable pageable);
}
