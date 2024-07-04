package io.thesun4sky.todoapp.service;

import java.util.List;
import java.util.Objects;

import io.thesun4sky.todoapp.dto.TodoResponseDTO;
import io.thesun4sky.todoapp.exception.TodoException;
import io.thesun4sky.todoapp.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.thesun4sky.todoapp.dto.TodoRequestDTO;
import io.thesun4sky.todoapp.entity.Todo;
import io.thesun4sky.todoapp.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {
	private final TodoRepository todoRepository;

	// 할일 생성
	@Transactional
	public TodoResponseDTO createTodo(UserDetailsImpl userDetails, TodoRequestDTO request) {
		Todo todo = Todo.builder()
				.title(request.getTitle())
				.content(request.getContent())
				.user(userDetails.getUser())
				.build();

		Todo savePost = todoRepository.save(todo);
		return new TodoResponseDTO(savePost);
	}

	// 할일 단건 조회
	public TodoResponseDTO getTodo(Long id) {
		Todo todo = getValidateTodo(id);
		return new TodoResponseDTO(todo);
	}

	// 할일 전체 조회
	public List<Todo> getTodos() {
		return todoRepository.findAll(Sort.by("createdAt").descending());
	}

	// 할일 수정
	@Transactional
	public TodoResponseDTO updateTodo(Long id, UserDetailsImpl userDetails, TodoRequestDTO requestDto) {
		Todo todo = getValidateTodo(id);
		checkPWAndGetTodo(todo, userDetails);

		todo.update(requestDto);
		todoRepository.save(todo);

		return new TodoResponseDTO(todo);
	}

	private void checkPWAndGetTodo(Todo todo, UserDetailsImpl userDetails) {
		if (!todo.getUser().getUsername().equals(userDetails.getUsername())) {
			throw new TodoException("작성자가 아니므로, 접근이 제한됩니다.");
		}
	}

	public void deleteTodo(Long id, UserDetailsImpl userDetails) {
		Todo todo = getValidateTodo(id);
		checkTodoWriter(todo, userDetails);
		todoRepository.delete(todo);
	}

	public Todo getValidateTodo(Long id) {
		return todoRepository.findById(id).orElseThrow(() ->
				new TodoException("게시글이 존재하지 않습니다."));
	}

	private void checkTodoWriter(Todo todo, UserDetailsImpl userDetails) {
		if (!todo.getUser().getUsername().equals(userDetails.getUsername())) {
			throw new TodoException("작성자가 아니므로, 접근이 제한됩니다.");
		}
	}
}
