package com.sparta.todolist.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//요청
@Getter
@Setter
@NoArgsConstructor

public class TodoRequestDto {
    private String title;
    private String username;
    private String password;
    private String description;
    private String createdAt;
    private String updatedAt;
}
