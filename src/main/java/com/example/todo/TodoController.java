package com.example.todo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TodoController {

    // ToDo一覧画面を表示する。
    @GetMapping("/todos")
    public String list() {
        return "todo/list";
    }

    // ToDo新規作成画面を表示する。
    @GetMapping("/todos/new")
    public String createForm() {
        return "todos/new";
    }

    // 指定したIDのToDo詳細画面を表示する。
    @GetMapping("/todos/{id}")
    public String detail(@PathVariable Long id) {
        return "todos/detail";
    }
}
