package com.example.todo.controller.task;

import com.example.todo.service.task.TaskEntity;
import com.example.todo.service.task.TaskService;
import com.example.todo.service.task.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/tasks")
    public String list(Model model) {
        var taskList = taskService.find()
                .stream()
                .map(TaskDTO::toDTO)
                .toList();
        model.addAttribute("taskList", taskList);
        return "tasks/list";
    }

    @GetMapping("/tasks/{id}")
    public String showDetail(@PathVariable("id") long taskid, Model model) {
        var taskEntity = taskService.findById(taskid)
                .orElseThrow(() -> new IllegalArgumentException("Task not find: id = " + taskid));
        model.addAttribute("task", TaskDTO.toDTO(taskEntity));
        return "tasks/detail";
    }

    @GetMapping("/tasks/creationForm")
    public String creationForm() {
        return "tasks/form";
    }

    @PostMapping("/tasks")
    public String create(TaskForm form, Model model) {
        var newEntity = new TaskEntity(null, form.summary(), form.description(), TaskStatus.valueOf(form.status()));
        taskService.create(newEntity);
        return list(model);
    }
}
