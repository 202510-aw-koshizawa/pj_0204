package com.example.todo;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> findAll() {
        return todoRepository.findAllByOrderByPriorityAsc();
    }

    public Todo createFromForm(TodoForm form) {
        Todo todo = new Todo();
        todo.setTitle(form.getTitle());
        todo.setDescription(form.getDescription());
        todo.setDueDate(form.getDueDate());
        todo.setPriority(form.getPriority());
        return todoRepository.save(todo);
    }
}
