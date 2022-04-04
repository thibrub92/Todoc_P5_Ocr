package com.cleanup.todoc.viewmodel;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repositories.TaskRepository;
import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private final LiveData<List<Task>> allTasks;
    private final TaskRepository taskRepository;

    public TaskViewModel(Context context) {
        super((Application) context);
        taskRepository = new TaskRepository(context);
        allTasks = taskRepository.getAllTasks();
    }

    public void insert(Task task) {
        taskRepository.insert(task);
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public void delete(Task task) {
        taskRepository.delete(task);
    }

    public void update(Task task) {
        taskRepository.update(task);
    }

}
