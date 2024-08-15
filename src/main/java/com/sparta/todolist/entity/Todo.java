package com.sparta.todolist.entity;

import com.sparta.todolist.dto.TodoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor


public class Todo {
    private Long id;
    private String title;
    private String username;
    private String password;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Todo(TodoRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.description = requestDto.getDescription();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void update(TodoRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.updatedAt = LocalDateTime.now();
    }
}


