package com.sparta.todolist.dto;

import com.sparta.todolist.entity.Todo;
import lombok.Getter;
import lombok.Setter;
import java.time.format.DateTimeFormatter;



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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.username = todo.getUsername();
        this.description = todo.getDescription();
        this.createdAt = todo.getCreatedAt().format(formatter);
        this.updatedAt = todo.getUpdatedAt().format(formatter);
    }


}