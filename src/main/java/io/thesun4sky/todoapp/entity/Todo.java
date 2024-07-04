package io.thesun4sky.todoapp.entity;

import java.time.LocalDateTime;
import java.util.List;

import io.thesun4sky.todoapp.dto.TodoRequestDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "todo")
public class Todo extends Timestamped{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	private String title;

	@Column(nullable = false)
	private String content;

	@OneToMany(mappedBy = "todo", orphanRemoval = true)
	private List<Comment> commentList;

	@Builder
	public Todo(User user, String title, String content, String password) {
		this.user = user;
		this.title = title;
		this.content = content;
	}

	public void update(TodoRequestDTO requestDto) {
		this.title = requestDto.getTitle();
		this.content = requestDto.getContent();
	}
}
