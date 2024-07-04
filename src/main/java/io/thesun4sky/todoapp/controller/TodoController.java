package io.thesun4sky.todoapp.controller;

import java.util.List;
import java.util.stream.Collectors;

import io.thesun4sky.todoapp.dto.TodoRequestDTO;
import io.thesun4sky.todoapp.dto.TodoResponseDTO;
import io.thesun4sky.todoapp.security.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@RequiredArgsConstructor
public class TodoController {

	public final TodoService todoService;

	@PostMapping
	public ResponseEntity<CommonResponseDto<TodoResponseDTO>> createTodo(@AuthenticationPrincipal UserDetailsImpl userDetails, @Valid @RequestBody TodoRequestDTO request) {
		TodoResponseDTO todoResponseDTO = todoService.createTodo(userDetails, request);

		CommonResponseDto<TodoResponseDTO> responseMessage = CommonResponseDto.<TodoResponseDTO>builder()
				.statusCode(HttpStatus.CREATED.value())
				.msg("게시글 생성이 완료되었습니다.")
				.data(todoResponseDTO)
				.build();

		return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CommonResponseDto<TodoResponseDTO>> getTodo(@PathVariable Long id) {
		TodoResponseDTO todoResponseDTO = todoService.getTodo(id);

		CommonResponseDto<TodoResponseDTO> responseMessage = CommonResponseDto.<TodoResponseDTO>builder()
				.statusCode(HttpStatus.OK.value())
				.msg("게시글 조회가 완료되었습니다.")
				.data(todoResponseDTO)
				.build();

		return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
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

	@PutMapping("/{id}")
	public ResponseEntity<CommonResponseDto<TodoResponseDTO>> updateTodo(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails, @Valid @RequestBody TodoRequestDTO requestDto) {
		TodoResponseDTO responseDTO = todoService.updateTodo(id, userDetails, requestDto);

		CommonResponseDto<TodoResponseDTO> responseMessage = CommonResponseDto.<TodoResponseDTO>builder()
				.statusCode(HttpStatus.OK.value())
				.msg("게시글 수정이 완료되었습니다.")
				.data(responseDTO)
				.build();

		return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<CommonResponseDto<Long>> deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
		todoService.deleteTodo(id, userDetails);

		CommonResponseDto<Long> responseMessage = CommonResponseDto.<Long>builder()
				.statusCode(HttpStatus.OK.value())
				.msg("게시글 삭제가 완료되었습니다.")
				.data(id)
				.build();

		return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
	}
}
