package com.sparta.todolist.repository;

import com.sparta.todolist.entity.Todo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TodoRepository {

    private final JdbcTemplate jdbcTemplate;

    public TodoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Todo save(Todo todo) {
        String sql = "INSERT INTO todo (title, username, password, description, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, todo.getTitle());
            ps.setString(2, todo.getUsername());
            ps.setString(3, todo.getPassword());
            ps.setString(4, todo.getDescription());
            ps.setTimestamp(5, Timestamp.valueOf(todo.getCreatedAt()));
            ps.setTimestamp(6, Timestamp.valueOf(todo.getUpdatedAt()));
            return ps;
        }, keyHolder);

        todo.setId(keyHolder.getKey().longValue());
        return todo;
    }

    public Todo findById(Long id) {
        String sql = "SELECT * FROM todo WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Todo todo = new Todo();
            todo.setId(rs.getLong("id"));
            todo.setTitle(rs.getString("title"));
            todo.setUsername(rs.getString("username"));
            todo.setPassword(rs.getString("password"));
            todo.setDescription(rs.getString("description"));
            todo.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            todo.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            return todo;
        }, id);
    }

    public List<Todo> findAll() {
        String sql = "SELECT * FROM todo";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Todo todo = new Todo();
            todo.setId(rs.getLong("id"));
            todo.setTitle(rs.getString("title"));
            todo.setUsername(rs.getString("username"));
            todo.setPassword(rs.getString("password"));
            todo.setDescription(rs.getString("description"));
            todo.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            todo.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            return todo;
        });
    }

    public void update(Todo todo) {
        String sql = "UPDATE todo SET title = ?, username = ?, description = ?, updated_at = ? WHERE id = ?";
        jdbcTemplate.update(sql, todo.getTitle(), todo.getUsername(), todo.getDescription(), Timestamp.valueOf(todo.getUpdatedAt()), todo.getId());
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM todo WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
