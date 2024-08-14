package com.sparta.todolist.entity;

import com.sparta.todolist.dto.TodoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor


public class Todo {
    private Long id;
    private String title;
    private String username;
    private String password;
    private String description;
    private String createdAt;
    private String updatedAt;

    public Todo(TodoRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.description = requestDto.getDescription();
        this.createdAt = requestDto.getCreatedAt();
        this.updatedAt = requestDto.getUpdatedAt();
    }
}


