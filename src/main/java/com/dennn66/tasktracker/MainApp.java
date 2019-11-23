package com.dennn66.tasktracker;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainApp {
    public static void main(String[] args) {
        TaskService tracker;

        try(SessionFactory factory = new Configuration()
                .configure("configs/hibernate.cfg.xml")
                .buildSessionFactory()){
            tracker = new TaskService(factory);
            tracker.test();
        }


    }
}
