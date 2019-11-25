package com.dennn66.tasktracker;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(TaskConfig.class);
        TaskService tracker = context.getBean("taskService", TaskService.class);
        tracker.test();
    }
}
