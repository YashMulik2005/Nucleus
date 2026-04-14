package com.example.Nucleus.listener;

import com.example.Nucleus.event.TaskActivityEvent;
import com.example.Nucleus.model.Activity;
import com.example.Nucleus.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class TaskActivityListener {

    @Autowired
    private ActivityRepository activityRepository;

    @EventListener
    @Async
    public void handleTaskActivity(TaskActivityEvent task){
        Activity activity = new Activity();
        activity.setUser(task.getUser());
        activity.setTask(task.getTask());
        activity.setMessage(task.getMsg());

        activityRepository.save(activity);
    }
}
