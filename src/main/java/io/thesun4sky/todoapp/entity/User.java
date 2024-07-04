package io.thesun4sky.todoapp.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "user")
public class User extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "varchar(30)")
    @Enumerated(value = EnumType.STRING)
    private UserType userType;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime statusChangedAt;

    @Column
    private String refreshToken;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Todo> todoList;

    @Builder
    public User(String nickname, String username, String password, UserType userType, LocalDateTime statusChangedAt) {
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.statusChangedAt = statusChangedAt;
    }

    @Transactional
    public void refreshTokenReset(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
