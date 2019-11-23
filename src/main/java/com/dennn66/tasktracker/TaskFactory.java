package com.dennn66.tasktracker;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class TaskFactory {
    private static final SessionFactory factory;
    static {
        factory = new Configuration()
                .configure("configs/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static SessionFactory getFactory() {
        return factory;
    }

    public static void close(){
        if(factory != null){
            factory.close();
        }
    }
}
