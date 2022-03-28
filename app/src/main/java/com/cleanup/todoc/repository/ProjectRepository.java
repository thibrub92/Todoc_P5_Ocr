package com.cleanup.todoc.repository;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.database.dao.TodocDatabase;
import com.cleanup.todoc.model.Project;
import java.util.List;

public class ProjectRepository {
    private LiveData<List<Project>> allProjects;
    private ProjectDao projectDao;

    public ProjectRepository(Context context) {
        TodocDatabase db = TodocDatabase.getINSTANCE(context);
        projectDao = db.projectDao();
        allProjects = projectDao.getAllProjects1();
    }

    public LiveData<List<Project>> getAllProjects1(){return allProjects;}
}
