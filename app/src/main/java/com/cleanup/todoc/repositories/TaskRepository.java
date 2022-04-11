package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Task;
import java.util.List;

public class TaskRepository {
    private TaskDao taskDao;

    public TaskRepository(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    // ---GET---
    public LiveData<List<Task>> getAllTasks() {
        return taskDao.getAllTasks();
    }

    public void insert(Task task) {
        taskDao.insert(task);
    }

    // ---DELETE---
    public void delete(Task task) {
        taskDao.deleteTask(task);
    }

    // ---UPDATE---
    public void update(Task task) {
        taskDao.updateTask(task);
    }
}
