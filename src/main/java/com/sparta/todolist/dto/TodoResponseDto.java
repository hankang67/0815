package com.sparta.todolist.dto;

import com.sparta.todolist.entity.Todo;
import lombok.Getter;
import lombok.Setter;

//여기가 응답
@Getter
@Setter


public class TodoResponseDto {
    private Long id;
    private String title;
    private String username;
    private String description;
    private String createdAt;
    private String updatedAt;

    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.username = todo.getUsername();
        this.description = todo.getDescription();
        this.createdAt = todo.getCreatedAt();
        this.updatedAt = todo.getUpdatedAt();
    }


}