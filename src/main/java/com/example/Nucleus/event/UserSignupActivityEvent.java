package com.example.Nucleus.event;

import com.example.Nucleus.model.User;

public class UserSignupActivityEvent {
    private User user;

    public UserSignupActivityEvent(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
