package com.example.todo.controller.task;

import com.example.todo.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public String list(TaskSearchForm searchForm, Model model) {
        var taskList = taskService.find(searchForm.toEntity())
                .stream()
                .map(TaskDTO::toDTO)
                .toList();
        model.addAttribute("taskList", taskList);
        model.addAttribute("searchDTO", searchForm.toDTO());
        return "tasks/list";
    }

    @GetMapping("/{id}")
    public String showDetail(
            @PathVariable("id") long taskId,
            @RequestParam(value = "no", required = false) Integer no,
            Model model
    ) {
        var taskDTO = taskService.findById(taskId)
                .map(TaskDTO::toDTO)
                .orElseThrow(TaskNotFoundException::new);

        model.addAttribute("task", taskDTO);
        model.addAttribute("no", no);
        model.addAttribute("id", taskId);

        return "tasks/detail";
    }

    @GetMapping("/creationForm")
    public String showCreationForm(@ModelAttribute TaskForm form, Model model) {
        model.addAttribute("mode", "CREATE");
        return "tasks/form";
    }

    @PostMapping
    public String create(@Validated TaskForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return showCreationForm(form, model);
        }
        taskService.create(form.toEntity());
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/editForm")
    public String showEditForm(@PathVariable("id") long id,
                               @RequestParam(value = "no", required = false) Integer no,
                               Model model) {
        var form = taskService.findById(id)
                .map(TaskForm::fromEntity)
                .orElseThrow(TaskNotFoundException::new);
        model.addAttribute("taskForm", form);
        model.addAttribute("mode", "EDIT");
        model.addAttribute("id", id);
        model.addAttribute("no", no);
        return "tasks/form";
    }

    @PutMapping("{id}") // PUT /tasks/{id}
    public String update(
            @PathVariable("id") long id,
            @RequestParam(value = "no", required = false) Integer no,
            @Validated @ModelAttribute TaskForm form,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "EDIT");
            model.addAttribute("id", id);
            model.addAttribute("no", no);
            return "tasks/form";
        }
        var entity = form.toEntity(id);
        taskService.update(entity);
        return "redirect:/tasks/" + id + "?no=" + no;
    }

    // POST /tasks/1 (hidden: _method: delete)
    // -> DELETE /tasks/1
    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") long id) {
        taskService.delete(id);
        return "redirect:/tasks";
    }
}
