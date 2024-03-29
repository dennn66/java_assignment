package com.dennn66.tasktracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TaskDB  implements TaskRepository {


    private SessionFactory factory;

    public TaskDB() {
    }

    @PostConstruct
    public void init(){
        createTableEx();
    }

    @Autowired
    @Qualifier(value = "factory")
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void addTask(Task task) {
        if (task == null) throw new NullPointerException();
        try(Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
        }
    }

    @Override
    public void updateTask(Task task) {
        try(Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
        }
    }

    @Override
    public void deleteTask(Task task) {
        try(Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.remove(task);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<Task> getTasks(String name, Task.Status status) {
        List<Task> results;
        try(Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Task> criteriaQuery = criteriaBuilder.createQuery(Task.class);
            Root<Task> root = criteriaQuery.from(Task.class);

            List<Predicate> predicates = new ArrayList<>();
            if (name != null) {
                predicates.add(criteriaBuilder.like(root.get("name"), name));
            }
            if (status != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }
            criteriaQuery.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
            Query<Task> query = session.createQuery(criteriaQuery);
            results = query.getResultList();
            session.getTransaction().commit();
        }
        return results;
    }

    @Override
    public Task getTask(Long id) {
        Task task;
        try(Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            task = session.get(Task.class, id);
            session.getTransaction().commit();
        }
        if (task == null) {
            throw new TaskNotFoundException("Task not found");
        }
        return task;
    }

    private void createTableEx() {
        //name, creator, assignee, description, status
        try(Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS tasks (\n" +
                    "        id  bigserial PRIMARY KEY,\n" +
                    "        name  VARCHAR(255),\n" +
                    "        creator  VARCHAR(255),\n" +
                    "        assignee  VARCHAR(255),\n" +
                    "        description  VARCHAR(255),\n" +
                    "        status  int\n" +
                    "    );").executeUpdate();
            session.getTransaction().commit();
        }
    }
    @Override
    public void clear() {
        try(Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.createNativeQuery("DELETE FROM tasks;").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
