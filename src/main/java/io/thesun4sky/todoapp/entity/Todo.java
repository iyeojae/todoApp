package io.thesun4sky.todoapp.entity;

import java.time.LocalDateTime;
import java.util.List;

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
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "todo_id", nullable = false)
	private Long todoId;

	private String title;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	private String userName;

	@Column(nullable = false)
	private String password;

	@OneToMany(mappedBy = "post", orphanRemoval = true)
	private List<Comment> commentList;

	private LocalDateTime createdAt;

	@Builder
	public Todo(String title, String content, String userName, String password) {
		this.title = title;
		this.content = content;
		this.userName = userName;
		this.password = password;
		this.createdAt = LocalDateTime.now();
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
