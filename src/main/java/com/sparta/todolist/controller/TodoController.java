package com.sparta.todolist.controller;

import com.sparta.todolist.dto.TodoRequestDto;
import com.sparta.todolist.dto.TodoResponseDto;
import com.sparta.todolist.entity.Todo;
import com.sparta.todolist.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/todos")

public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

//    private final Map<Long, Todo> todoList = new HashMap<>();

    // To do 생성하기

    @PostMapping
    public TodoResponseDto createTodo(@RequestBody TodoRequestDto requestDto){
        return todoService.createTodo(requestDto);
    }

    //To do 선택한 일정 조회하기
    @GetMapping("/{id}")
    public TodoResponseDto getTodo(@PathVariable Long id) {
        return todoService.getTodoById(id);
    }

    //To do 일정 목록 조회하기
    @GetMapping
    public List<TodoResponseDto> getTodos(@RequestParam(required = false) String updatedAt,
                                          @RequestParam(required = false) String username) {
        return todoService.getTodos(updatedAt, username);
    }

    //선택한 일정 수정하기
    @PutMapping("/{id}")
    public TodoResponseDto updateTodo(@PathVariable Long id, @RequestBody TodoRequestDto requestDto) {
        return todoService.updateTodo(id, requestDto);
    }

    //선택한 일정 삭제하기
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable Long id, @RequestParam String password) {
        todoService.deleteTodoById(id, password);
    }


}
