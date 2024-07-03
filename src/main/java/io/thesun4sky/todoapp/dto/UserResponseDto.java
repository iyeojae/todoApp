package io.thesun4sky.todoapp.dto;

import io.thesun4sky.todoapp.entity.User;

import java.time.LocalDateTime;

public class UserResponseDto {
    private Long id;
    private String userId;
    private String nickname;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.userId = user.getUserId();
        this.nickname = user.getNickname();
        this.username = user.getUsername();
        this.createdAt = user.getCreatedAt();
        this.modifiedAt = user.getModifiedAt();
    }
}
