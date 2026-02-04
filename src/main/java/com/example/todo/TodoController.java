package com.example.todo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // ToDo一覧画面を表示する。
    @GetMapping("/todos")
    public String list(Model model) {
        model.addAttribute("todos", todoService.findAll());
        return "todo/list";
    }

    // ToDo新規作成画面を表示する。
    @GetMapping("/todos/new")
    public String createForm(Model model, @ModelAttribute("todoForm") TodoForm todoForm) {
        if (!model.containsAttribute("todoForm")) {
            model.addAttribute("todoForm", new TodoForm());
        }
        return "todo/form";
    }

    // 指定したIDのToDo詳細画面を表示する。
    @GetMapping("/todos/{id}")
    public String detail(@PathVariable Long id) {
        return "todo/detail";
    }

    // フォーム送信内容を受け取り、確認画面へリダイレクトする。
    @PostMapping("/todos/confirm")
    public String confirm(@ModelAttribute TodoForm todoForm, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("todoForm", todoForm);
        return "redirect:/todos/confirm";
    }

    // 確認画面を表示する。
    @GetMapping("/todos/confirm")
    public String confirmView(@ModelAttribute("todoForm") TodoForm todoForm, Model model) {
        if (!model.containsAttribute("todoForm")) {
            return "redirect:/todos/new";
        }
        return "todo/confirm";
    }

    // 確認画面から入力画面へ戻る（入力値保持）。
    @PostMapping("/todos/back")
    public String back(@ModelAttribute TodoForm todoForm, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("todoForm", todoForm);
        return "redirect:/todos/new";
    }

    // 確認画面から登録し、一覧画面へ遷移する。
    @PostMapping("/todos/complete")
    public String complete(@ModelAttribute TodoForm todoForm, RedirectAttributes redirectAttributes) {
        todoService.createFromForm(todoForm);
        redirectAttributes.addFlashAttribute("message", "登録が完了しました");
        return "redirect:/todos";
    }
}
