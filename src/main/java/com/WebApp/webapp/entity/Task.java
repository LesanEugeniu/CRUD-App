package com.WebApp.webapp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table
public class Task {
    @Id
    @SequenceGenerator(
            name = "task_sequence",
            sequenceName = "task_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_sequence"
    )
    private Long Id;

    @Size(min = 4, max = 25, message = "required")
    private String task;

    public Long getId() {
        return Id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Task(){};
    public Task(String task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "Task{" +
                "Id=" + Id +
                ", task='" + task + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task1 = (Task) o;
        return Objects.equals(Id, task1.Id) && Objects.equals(task, task1.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, task);
    }
}
