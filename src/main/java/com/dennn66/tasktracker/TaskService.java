package com.dennn66.tasktracker;

import org.hibernate.SessionFactory;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


public class TaskService {
    private TaskRepository taskRepository;

    public TaskService(SessionFactory factory) {
        taskRepository = new TaskDB(factory);
    }

    public void addTask(Task task)          {taskRepository.addTask(task);}
    public void updateTask(Task task)       {taskRepository.updateTask(task);}

    public void exportTasks(String filename, List <Task> tasks){
        try(DataOutputStream out = new DataOutputStream(new FileOutputStream(filename))){
            System.out.println("exporting " + tasks.size() + " tasks to " + filename + "...");
            tasks.forEach(task -> {
                try {
                    System.out.println("exporting task " + task.getId() + "...");
                    out.writeLong(task.getId());
                    out.writeUTF(task.getName());
                    out.writeUTF(task.getCreator());
                    out.writeUTF(task.getAssignee());
                    out.writeUTF(task.getDescription());
                    out.writeUTF(task.getStatus().name());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List <Task> importTasks(String filename){
        ArrayList<Task> tasks = new ArrayList<>();
        try(DataInputStream in = new DataInputStream(new FileInputStream(filename))){
            System.out.println("importing tasks from " + filename + "...");
            while(true) {
                long id = in.readLong();//id,
                System.out.println("importing task " + id + "...");
                String name = in.readUTF();// название,
                String creator = in.readUTF();// имя владельца задачи,
                String assignee = in.readUTF(); // имя исполнителя,
                String description = in.readUTF(); // описание,
                String status = in.readUTF(); // статус

                Task task = new Task(name, creator, description);
                task.setAssignee(assignee);
                task.setStatus(Task.Status.valueOf(status));
                tasks.add(task);
                System.out.println(task);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Tasks import is completed. Loaded " + tasks.size() + " tasks");
        }
        return tasks;
    }

    public List<Task> findTasksByStatus(Task.Status status)       {
        return taskRepository.getTasks(null, status).stream().
                filter(t -> t.getStatus() == status).collect(Collectors.toList());
    }

    public List<Task> sortTasksByStatus()       {
        return taskRepository.getTasks(null, null).stream().sorted().collect(Collectors.toList());
    }

    public long countTasksByStatus(Task.Status status)       {
        return findTasksByStatus(status).size();
    }

    public Task findTask(Long taskId)       {
        Task task = taskRepository.getTask(taskId);
        if(task == null) throw new TaskNotFoundException("Task not found");
        return task;
    }
    public Task findTask(String taskName)   {
        return taskRepository.getTasks(taskName, null).stream().
                findFirst().
                orElseThrow(() -> new TaskNotFoundException("Task not found"));
    }
    public void deleteTask(Long taskId)     {taskRepository.deleteTask(findTask(taskId));}
    public void deleteTask(String taskName) {taskRepository.deleteTask(findTask(taskName));}

    @Override
    public String toString() {
        List <Task> tasks = taskRepository.getTasks(null, null);
        StringBuilder taskList = new StringBuilder();
        for (Task task : tasks) {
            if (task != null) taskList.append(task).append("\r\n");
        }
        return taskList.toString();
    }

    public void info(){
        System.out.println(this);
    }

    public void test(){
        taskRepository.clear();

        for(int counter = 0; counter< 10; counter++){
            Task task = new Task( "Important task #" + counter, "Bill",  "very important task");
            try {
                addTask(task);
                task.info();
            } catch (TaskExistException e){
                System.out.println(e.getMessage());
            }
        }
        try {
            System.out.println("Adding null task...");
            addTask(null);
        } catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        Task task = new Task("Important task #11", "Bill",  "very important task");
        try {
            System.out.println("Adding new task...");
            addTask(task);
            System.out.println("Id of new task is " + task.getId());
        } catch (TaskExistException e){
            System.out.println(e.getMessage());
        }

        System.out.println("Find existing task:");
        Task t = findTask(task.getId());
        t.info();
        t.setStatus(Task.Status.COMPLETED);
        updateTask(t);
        System.out.println("Find not existing task:");
        try {
            findTask("Important task #12").info();
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }

        findTask("Important task #4").setAssignee("John");
        //findTask("Important task #1006").setAssignee("John");
        findTask("Important task #8").closeTask();
        //findTask("Important task #1002").setAssignee("John");
        deleteTask(findTask("Important task #3").getId());
        try {
            deleteTask(15L);
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
        deleteTask("Important task #7");
        try {
            deleteTask("Important task #1015");
        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
        info();
        //System.out.println(findTasksByStatus(com.dennn66.tasktracker.Task.Status.ASSIGNED));
        System.out.println("findTasksByStatus");
        System.out.println(findTasksByStatus(Task.Status.ASSIGNED));
        System.out.println("sortTasksByStatus");
        System.out.println(sortTasksByStatus());
        System.out.println("countTasksByStatus");
        System.out.println(countTasksByStatus(Task.Status.ASSIGNED));
        exportTasks("tasks.data", taskRepository.getTasks(null, null));
        System.out.println(importTasks("tasks.data"));
    }
}
