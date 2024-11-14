package com.example.tasktracker.Controller;

import com.example.tasktracker.ApiResponse.ApiResponse;
import com.example.tasktracker.Model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/task-tracker")
public class TaskTrackerController {

    ArrayList<Task> tasks = new ArrayList<>();

    @GetMapping("/get-tasks")
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    @PostMapping("/add-task")
    public ApiResponse addTask(@RequestBody Task task) {
        tasks.add(task);

        return new ApiResponse("Task has been added successfully!");
    }

    @PutMapping("/update-task/{index}")
    public ApiResponse updateTask(@PathVariable int index, @RequestBody Task task) {
        tasks.set(index, task);

        return new ApiResponse("Task at index " + index + " has been updated successfully!");
    }

    @DeleteMapping("/delete-task/{index}")
    public ApiResponse deleteTask(@PathVariable int index) {
        tasks.remove(index);

        return new ApiResponse("Task at index " + index + " has been deleted successfully!");
    }

    @PutMapping("/change-status/{index}")
    public ApiResponse changeStatus(@PathVariable int index) {
        if (tasks.get(index).getStatus().equals("Not Done"))
            tasks.get(index).setStatus("Done");
        else tasks.get(index).setStatus("Not Done");

        return new ApiResponse("Task at index " + index + " has been changed successfully!");
    }

    @GetMapping("/get-task")
    public Task getTaskByTitle(@RequestBody String title) {
        for (Task task : tasks)
            if (task.getTitle().contains(title))
                return task;
        return null;
    }
}
