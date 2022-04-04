package com.cleanup.todoc.injection;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.cleanup.todoc.database.dao.TodocDatabase;
import com.cleanup.todoc.repositories.ProjectRepository;
import com.cleanup.todoc.repositories.TaskRepository;
import com.cleanup.todoc.viewmodel.ProjectViewModel;
import org.jetbrains.annotations.NotNull;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final ProjectRepository projectRepository;

    private final TaskRepository taskRepository;

    private final Executor executor;

    private static ViewModelFactory factory;

    public static ViewModelFactory getInstance(Context context) {

        if (factory == null) {
            synchronized (ViewModelFactory.class) {
                if (factory == null) {
                    factory = new ViewModelFactory(context);
                }
            }
        }
        return factory;
    }

    private ViewModelFactory(Context context) {

        TodocDatabase database = TodocDatabase.getINSTANCE(context);

        this.projectRepository = new ProjectRepository(database.projectDao());
        this.taskRepository = new TaskRepository(database.taskDao());
        this.executor = Executors.newSingleThreadExecutor();

    }

    @Override

    @NotNull

    public <T extends ViewModel> T create(Class<T> modelClass) {

        if (modelClass.isAssignableFrom(ProjectViewModel.class)) {

            return (T) new ProjectViewModel(LiveData, taskRepository, executor);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
