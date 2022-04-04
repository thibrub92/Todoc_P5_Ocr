package com.cleanup.todoc.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;
import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.database.dao.TodocDatabase;
import com.cleanup.todoc.model.Task;
import java.util.List;

public class TaskRepository {
    private TaskDao taskDao;
    private LiveData<List<Task>> allTasks;

    public TaskRepository(Context context) {
        TodocDatabase db = TodocDatabase.getINSTANCE(context);
        taskDao = db.taskDao();
        allTasks = taskDao.getAllTasks();
    }

    // ---GET---
    public LiveData<List<Task>> getAllTasks(){
        return allTasks;
    }

    public void insert(Task task){
        TodocDatabase.databaseWriteExecutor.execute(() -> taskDao.insert(task));
    }

    // ---DELETE---
    public void delete(Task task){
        TodocDatabase.databaseWriteExecutor.execute(() -> taskDao.deleteTask(task));
    }

    // ---UPDATE---
    public void update(Task task){
        TodocDatabase.databaseWriteExecutor.execute(() -> taskDao.updateTask(task));
    }
}
