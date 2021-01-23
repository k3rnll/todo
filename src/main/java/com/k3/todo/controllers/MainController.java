package com.k3.todo.controllers;

import com.k3.todo.models.Task;
import com.k3.todo.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Controller
public class MainController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Глагне");
        Iterable<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "home";
    }

    @PostMapping("/")
    public String taskAdd(@RequestParam String title, Model model) {
        Task task = new Task(title);
        taskRepository.save(task);
        return "redirect:/";
    }

    @GetMapping("/done/{id}")
    public String taskDone(@PathVariable(value = "id") long id, Model model) {
        if (!taskRepository.existsById(id))
            return "redirect:/";
        Task task = taskRepository.findById(id).orElseThrow();
        task.setStatus("готово");
        taskRepository.save(task);
        return "redirect:/";
    }

    @GetMapping("/del/{id}")
    public String taskDel(@PathVariable(value = "id") long id, Model model) {
        if (!taskRepository.existsById(id))
            return "redirect:/";
        taskRepository.deleteById(id);
        return "redirect:/";
    }
}