package io.thesun4sky.todoapp.dto;

import io.thesun4sky.todoapp.entity.Todo;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TodoRequestDTO {
	@NotBlank
	private String title;

	@NotBlank
	private String content;
}
