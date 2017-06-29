package com.alex.spring.service;

import com.alex.spring.Model.TaskList;
import com.alex.spring.dao.Dao;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Alex on 28.06.2017.
 */
@org.springframework.stereotype.Service
public class ServiceImpl implements Service {

    private Dao dao;

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    @Transactional
    public void addTask(TaskList taskList) {
this.dao.addTask(taskList);
    }
    @Transactional
    public void removeTask(int id) {
this.dao.removeTask(id);
    }
    @Transactional
    public void updateTask(TaskList taskList) {
this.dao.updateTask(taskList);
    }
    @Transactional
    public TaskList getTaskById(int id) {
        return this.dao.getTaskById(id);
    }
    @Transactional
    public List<TaskList> getListTasks(String status) {
        return this.dao.getListTasks(status);
    }
}
