package com.example.Nucleus.event;

import com.example.Nucleus.model.Task;
import com.example.Nucleus.model.User;

public class TaskActivityEvent {
    private String msg;
    private User user;
    private Task task;

    public TaskActivityEvent(String msg, User user, Task task) {
        this.msg = msg;
        this.user = user;
        this.task = task;
    }

    public String getMsg() {
        return msg;
    }

    public User getUser() {
        return user;
    }

    public Task getTask() {
        return task;
    }
}
