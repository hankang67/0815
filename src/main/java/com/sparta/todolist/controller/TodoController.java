package com.sparta.todolist.controller;

import com.sparta.todolist.dto.TodoRequestDto;
import com.sparta.todolist.dto.TodoResponseDto;
import com.sparta.todolist.entity.Todo;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")

public class TodoController {

    private final Map<Long, Todo> todoList = new HashMap<>();


    // To do 생성하기
    @PostMapping("/todo")
    public TodoResponseDto createTodo(@RequestBody TodoRequestDto requestDto){

        //1. RequestDto -> Entity)
        Todo todo = new Todo(requestDto);

        //데이터베이스에 저장

        // 3. Entity -> ResponseDto
        TodoResponseDto responseDto = new TodoResponseDto(todo);

        // 4. TodoResponseDto 객체 반환
        return responseDto;

    }

    //To do 조회하기



}
