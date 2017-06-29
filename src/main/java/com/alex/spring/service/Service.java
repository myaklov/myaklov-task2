package com.alex.spring.service;

import com.alex.spring.Model.TaskList;

import java.util.List;

/**
 * Created by Alex on 28.06.2017.
 */
public interface Service {

    void addTask(TaskList taskList);

    void removeTask(int id);

    void updateTask(TaskList taskList);

    TaskList getTaskById(int id);

    List<TaskList> getListTasks(String status);

}
