package com.cleanup.todoc.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.model.TaskWithProject;
import com.cleanup.todoc.repositories.ProjectRepository;
import com.cleanup.todoc.repositories.TaskRepository;
import java.util.List;
import java.util.concurrent.Executor;

public class MainViewModel extends ViewModel {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final Executor executor;

    public MainViewModel(ProjectRepository projectRepository,
                         TaskRepository taskRepository,
                         Executor executor) {
        this.projectRepository = projectRepository;
        this.taskRepository = taskRepository;
        this.executor = executor;
    }

    public LiveData<List<Project>> getAllProjects() {
        return projectRepository.getAllProjects();
    }

    public void insertProject(Project project) {
        executor.execute(() -> projectRepository.insert(project));
    }

    public LiveData<List<TaskWithProject>> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    public void insertTask(Task task) {
        executor.execute(() -> taskRepository.insert(task));
    }

    public void deleteTask(Task task) {
        executor.execute(() -> taskRepository.delete(task));
    }

    public void updateTask(Task task) {
        executor.execute(() -> taskRepository.update(task));
    }
}
