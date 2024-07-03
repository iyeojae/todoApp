package io.thesun4sky.todoapp.dto;

import java.time.LocalDateTime;

import io.thesun4sky.todoapp.entity.Todo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoResponseDTO {

	private Long todoId;
	private String title;

	private String content;

	private String userName;

	private LocalDateTime createdAt;

	private LocalDateTime modifiedAt;

	public TodoResponseDTO(Todo todo) {
		this.todoId = todo.getTodoId();
		this.title = todo.getTitle();
		this.content = todo.getContent();
		this.userName = todo.getUserName();
		this.createdAt = todo.getCreatedAt();
		this.modifiedAt = todo.getModifiedAt();
	}
}
