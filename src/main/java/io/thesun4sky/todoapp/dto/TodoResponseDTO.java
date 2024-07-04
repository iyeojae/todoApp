package io.thesun4sky.todoapp.dto;

import java.time.LocalDateTime;

import io.thesun4sky.todoapp.entity.Todo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoResponseDTO {
	private String username;

	private String title;

	private String content;

	private LocalDateTime createdAt;

	private LocalDateTime modifiedAt;

	public TodoResponseDTO(Todo todo) {
		this.username = todo.getUser().getUsername();
		this.title = todo.getTitle();
		this.content = todo.getContent();
		this.createdAt = todo.getCreatedAt();
		this.modifiedAt = todo.getModifiedAt();
	}
}
