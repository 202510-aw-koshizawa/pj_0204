package com.example.todo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TodoController {

    // ToDo一覧画面を表示する。
    @GetMapping("/todos")
    public String list() {
        return "todo/list";
    }

    // ToDo新規作成画面を表示する。
    @GetMapping("/todos/new")
    public String createForm(Model model) {
        model.addAttribute("todoForm", new TodoForm());
        return "todo/form";
    }

    // 指定したIDのToDo詳細画面を表示する。
    @GetMapping("/todos/{id}")
    public String detail(@PathVariable Long id) {
        return "todo/detail";
    }

    // フォーム送信内容を受け取り、確認画面へ遷移する。
    @PostMapping("/todos/confirm")
    public String confirm(@ModelAttribute TodoForm todoForm, Model model) {
        model.addAttribute("todoForm", todoForm);
        return "todo/confirm";
    }
}
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
