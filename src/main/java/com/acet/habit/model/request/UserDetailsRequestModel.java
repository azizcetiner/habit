package com.acet.habit.model.request;

import com.acet.habit.entity.TaskEntity;

import java.util.List;

public class UserDetailsRequestModel {

    private String username;
    private String email;
    private String password;
    private List<TaskEntity> tasks;


    public List<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskEntity> tasks) {
        this.tasks = tasks;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


}
