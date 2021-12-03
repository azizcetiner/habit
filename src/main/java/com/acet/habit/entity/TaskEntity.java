package com.acet.habit.entity;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class TaskEntity {


    private boolean done;
    private Date dueDate;
    private String taskDescription;
    private String taskDetail;

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskDetail() {
        return taskDetail;
    }

    public void setTaskDetail(String taskDetail) {
        this.taskDetail = taskDetail;
    }
    // private user .. reference

}
