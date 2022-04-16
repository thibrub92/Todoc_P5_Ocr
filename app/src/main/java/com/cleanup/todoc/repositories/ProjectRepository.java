package com.cleanup.todoc.repositories;

import androidx.lifecycle.LiveData;
import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.model.Project;
import java.util.List;

public class ProjectRepository {

    private ProjectDao projectDao;

    public ProjectRepository(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public LiveData<List<Project>> getAllProjects() {
        return projectDao.getAllProjects();
    }

    public void insert(Project project) {
        projectDao.insert(project);
    }
}
