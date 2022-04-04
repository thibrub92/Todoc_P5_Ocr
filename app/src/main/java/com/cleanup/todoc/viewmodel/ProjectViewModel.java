package com.cleanup.todoc.viewmodel;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.repositories.ProjectRepository;
import java.util.List;

public class ProjectViewModel extends AndroidViewModel {

    private final LiveData<List<Project>> allProjects;
    private final ProjectRepository repository;

    public ProjectViewModel(Context context) {
        super((Application) context);
        repository = new ProjectRepository(context);
        allProjects = repository.getAllProjects();
    }

    public LiveData<List<Project>> getAllProjects() {
        return allProjects;
    }
}
