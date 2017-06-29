package com.alex.spring.Model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Alex on 29.06.2017.
 */
@Entity
@Table(name = "list", schema = "test")
public class TaskList implements Serializable{
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    @Column(name = "task", nullable = false, length = 100)
    private String task;
    @Basic
    @Column(name = "status", nullable = false, length = 10)
    private String status;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskList taskList = (TaskList) o;

        if (id != taskList.id) return false;
        if (task != null ? !task.equals(taskList.task) : taskList.task != null) return false;
        if (status != null ? !status.equals(taskList.status) : taskList.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (task != null ? task.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
