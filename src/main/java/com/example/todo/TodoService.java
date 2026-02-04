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
        return todoRepository.findAllByOrderByCreatedAtDesc();
    }

    public Todo createFromForm(TodoForm form) {
        Todo todo = new Todo();
        todo.setTitle(form.getTitle());
        todo.setDescription(form.getDescription());
        todo.setDueDate(form.getDueDate());
        todo.setPriority(form.getPriority());
        return todoRepository.save(todo);
    }

    public TodoForm getFormById(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException(id));
        TodoForm form = new TodoForm();
        form.setTitle(todo.getTitle());
        form.setDescription(todo.getDescription());
        form.setDueDate(todo.getDueDate());
        form.setPriority(todo.getPriority());
        form.setVersion(todo.getVersion());
        return form;
    }

    public void updateFromForm(Long id, TodoForm form) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException(id));
        todo.setTitle(form.getTitle());
        todo.setDescription(form.getDescription());
        todo.setDueDate(form.getDueDate());
        todo.setPriority(form.getPriority());
        todo.setVersion(form.getVersion());
        todoRepository.save(todo);
    }

    public boolean toggleCompleted(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new TodoNotFoundException(id));
        boolean next = !Boolean.TRUE.equals(todo.getCompleted());
        todo.setCompleted(next);
        todoRepository.save(todo);
        return next;
    }

    public void deleteById(Long id) {
        if (!todoRepository.existsById(id)) {
            throw new TodoNotFoundException(id);
        }
        todoRepository.deleteById(id);
    }
}
