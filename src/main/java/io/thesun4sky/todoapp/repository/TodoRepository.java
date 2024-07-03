package io.thesun4sky.todoapp.repository;

import io.thesun4sky.todoapp.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
