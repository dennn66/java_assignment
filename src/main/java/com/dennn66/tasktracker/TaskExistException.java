package com.dennn66.tasktracker;

public class TaskExistException extends RuntimeException {
    public TaskExistException(String s) {
        super(s);
    }
}
