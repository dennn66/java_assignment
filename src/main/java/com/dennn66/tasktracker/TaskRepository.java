package com.dennn66.tasktracker;

import java.util.List;

public interface TaskRepository {
    void addTask(Task task) ;
    void updateTask(Task task);
    void deleteTask(Task task);
    Task getTask(Long id);
    List<Task> getTasks(String name, Task.Status status);
    void clear();
}
