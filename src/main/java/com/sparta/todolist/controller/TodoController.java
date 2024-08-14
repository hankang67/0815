package com.sparta.todolist.controller;

import com.sparta.todolist.dto.TodoRequestDto;
import com.sparta.todolist.dto.TodoResponseDto;
import com.sparta.todolist.entity.Todo;

import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")

public class TodoController {

    private final Map<Long, Todo> todoList = new HashMap<>();

    // To do 생성하기
    //1단계부터 고난 시작...
    // ID 자동생성 (how?)
    // 날짜 자동생성(날짜와 시간)

    @PostMapping("/todo")
    public TodoResponseDto createTodo(@RequestBody TodoRequestDto requestDto){

        //1. RequestDto -> Entity)
        Todo todo = new Todo(requestDto);

        //데이터베이스에 저장
        // Memo Max ID Check
        Long maxId = todoList.size() > 0 ? Collections.max(todoList.keySet()) + 1 : 1;
        todo.setId(maxId);

        // DB 저장
        todoList.put(todo.getId(), todo);

        // 3. Entity -> ResponseDto
        TodoResponseDto responseDto = new TodoResponseDto(todo);

        // 4. TodoResponseDto 객체 반환
        return responseDto;

    }

    //To do 선택한 일정 조회하기
    //1. 선택한 일정 단건의 정보를 조회할 수 있습니다.
    //2. 일정의 고유 식별자(ID)를 사용하여 조회합니다.


    //To do 일정 목록 조회하기
    @GetMapping("/read")
    public List<TodoResponseDto> getMemos() {
        // Map To List
        List<TodoResponseDto> responseList = todoList.values().stream()
                .map(TodoResponseDto::new).toList();

        return responseList;
    }

    //선택한 일정 수정하기

    //선택한 일정 삭제하기


}
