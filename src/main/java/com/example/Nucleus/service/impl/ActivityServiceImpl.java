package com.example.Nucleus.service.impl;

import com.example.Nucleus.model.Activity;
import com.example.Nucleus.repository.ActivityRepository;
import com.example.Nucleus.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public Activity addActivity(Activity activity) {
        return activityRepository.save(activity);
    }
}
