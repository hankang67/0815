package com.sparta.todolist.service;

import com.sparta.todolist.dto.TodoRequestDto;
import com.sparta.todolist.dto.TodoResponseDto;
import com.sparta.todolist.entity.Todo;
import com.sparta.todolist.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public TodoResponseDto createTodo(TodoRequestDto requestDto) {
        Todo todo = new Todo(requestDto);
        Todo savedTodo = todoRepository.save(todo);
        return new TodoResponseDto(savedTodo);
    }

    public TodoResponseDto getTodoById(Long id) {
        Todo todo = todoRepository.findById(id);
        return new TodoResponseDto(todo);
    }

    public List<TodoResponseDto> getTodos(String updatedAt, String username) {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream()
                .filter(todo -> (updatedAt == null || todo.getUpdatedAt().toLocalDate().toString().startsWith(updatedAt)) &&
                        (username == null || todo.getUsername().equals(username)))
                .sorted((a, b) -> b.getUpdatedAt().compareTo(a.getUpdatedAt()))
                .map(TodoResponseDto::new)
                .collect(Collectors.toList());
    }

    public TodoResponseDto updateTodo(Long id, TodoRequestDto requestDto) {
        Todo todo = todoRepository.findById(id);
        if (!todo.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        todo.update(requestDto);
        todoRepository.update(todo);
        return new TodoResponseDto(todo);
    }

    public void deleteTodoById(Long id, String password) {
        Todo todo = todoRepository.findById(id);
        if (!todo.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        todoRepository.deleteById(id);
    }
}
