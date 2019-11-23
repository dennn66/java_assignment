package com.dennn66.tasktracker;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.dennn66.tasktracker"})
public class TaskConfig {
    @Bean
    public SessionFactory factory() {return TaskFactory.getFactory();}
}
