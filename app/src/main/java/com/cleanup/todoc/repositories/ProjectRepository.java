package com.cleanup.todoc.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;
import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TodocDatabase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class ProjectRepository {

    private LiveData<List<Project>> allProjects;
    private ProjectDao projectDao;

    public ProjectRepository(Context context) {
        TodocDatabase db = TodocDatabase.getINSTANCE(context);
        projectDao = db.projectDao();
        allProjects = projectDao.getAllProjects();
    }

    public LiveData<List<Project>> getAllProjects() {
        return allProjects;
    }

    public void insert(Project project) {
        TodocDatabase.databaseWriteExecutor.execute(() -> projectDao.insert(project));
    }
}
