package io.thesun4sky.todoapp.controller;

import java.util.List;
import java.util.stream.Collectors;

import io.thesun4sky.todoapp.dto.TodoRequestDTO;
import io.thesun4sky.todoapp.dto.TodoResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.thesun4sky.todoapp.dto.CommonResponseDto;
import io.thesun4sky.todoapp.entity.Todo;
import io.thesun4sky.todoapp.service.TodoService;
import lombok.AllArgsConstructor;

@RequestMapping("/api/todo")
@RestController
@AllArgsConstructor
public class TodoController {

	public final TodoService todoService;

	@PostMapping
	public ResponseEntity<CommonResponseDto<TodoResponseDTO>> postTodo(@RequestBody TodoRequestDTO dto) {
		Todo todo = todoService.createTodo(dto);
		TodoResponseDTO response = new TodoResponseDTO(todo);
		return ResponseEntity.ok()
			.body(CommonResponseDto.<TodoResponseDTO>builder()
				.statusCode(HttpStatus.OK.value())
				.msg("생성이 완료 되었습니다.")
				.data(response)
			.build());
	}

	@GetMapping("/{todoId}")
	public ResponseEntity<CommonResponseDto<TodoResponseDTO>> getTodo(@PathVariable Long todoId) {
		Todo todo = todoService.getTodo(todoId);
		TodoResponseDTO response = new TodoResponseDTO(todo);
		return ResponseEntity.ok()
			.body(CommonResponseDto.<TodoResponseDTO>builder()
				.statusCode(HttpStatus.OK.value())
				.msg("단건 조회가 완료 되었습니다.")
				.data(response)
				.build());
	}

	@GetMapping
	public ResponseEntity<CommonResponseDto<List<TodoResponseDTO>>> getTodos() {
		List<Todo> todos = todoService.getTodos();
		List<TodoResponseDTO> response = todos.stream()
			.map(TodoResponseDTO::new)
			.collect(Collectors.toList());
		return ResponseEntity.ok()
			.body(CommonResponseDto.<List<TodoResponseDTO>>builder()
				.statusCode(HttpStatus.OK.value())
				.msg("목록 조회이 완료 되었습니다.")
				.data(response)
				.build());
	}

	@PutMapping("/{todoId}")
	public ResponseEntity<CommonResponseDto<TodoResponseDTO>> putTodo(@PathVariable Long todoId, @RequestBody TodoRequestDTO dto) {
		Todo todo = todoService.updateTodo(todoId, dto);
		TodoResponseDTO response = new TodoResponseDTO(todo);
		return ResponseEntity.ok()
			.body(CommonResponseDto.<TodoResponseDTO>builder()
			.statusCode(HttpStatus.OK.value())
			.msg("수정이 완료 되었습니다.")
			.data(response)
			.build());
	}

	@DeleteMapping("/{todoId}")
	public ResponseEntity<CommonResponseDto> deleteTodo(@PathVariable Long todoId, @RequestBody TodoRequestDTO dto) {
		todoService.deleteTodo(todoId, dto.getPassword());
		return ResponseEntity.ok().body(CommonResponseDto.builder()
			.statusCode(HttpStatus.OK.value())
			.msg("삭제가 완료 되었습니다.")
			.build());
	}
}
