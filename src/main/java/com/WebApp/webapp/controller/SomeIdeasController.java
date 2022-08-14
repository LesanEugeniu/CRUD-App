package com.WebApp.webapp.controller;

import com.WebApp.webapp.entity.Task;
import com.WebApp.webapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/someIdeas")
public class SomeIdeasController {
    private final TaskService taskService;

    @Autowired
    public SomeIdeasController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(path = "/todoapp")
    public String toDoList(Model model){
        model.addAttribute("taskModel", new Task());
        model.addAttribute("listOfTasks", taskService.getAllTasks());
        return "/view/task/todoapp";
    }

    @PostMapping(path = "/add")
    public String toDoListAdd(@Valid @ModelAttribute("taskModel") Task task){
        taskService.addNewTask(task);
        return "redirect:/someIdeas/todoapp";
    }

    @PostMapping(path = "/delete/{id}")
    public String toDoListDelete(@PathVariable("id") Long id){
        taskService.deleteTask(id);
        return "redirect:/someIdeas/todoapp";
    }
}
