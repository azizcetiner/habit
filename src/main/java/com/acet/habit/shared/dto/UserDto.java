package com.acet.habit.shared.dto;

import com.acet.habit.entity.TaskEntity;

import java.io.Serializable;
import java.util.List;

public class UserDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String userId;
    private String username;
    private String email;
    private String password;
    private String encryptedPassword;
    private String emaiLVerificationToken;
    private List<TaskEntity> tasks;
    private boolean emailVerificationStatus = false;

    public List<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskEntity> tasks) {
        this.tasks = tasks;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getEmaiLVerificationToken() {
        return emaiLVerificationToken;
    }

    public void setEmaiLVerificationToken(String emaiLVerificationToken) {
        this.emaiLVerificationToken = emaiLVerificationToken;
    }

    public boolean isEmailVerificationStatus() {
        return emailVerificationStatus;
    }

    public void setEmailVerificationStatus(boolean emailVerificationStatus) {
        this.emailVerificationStatus = emailVerificationStatus;
    }

}
