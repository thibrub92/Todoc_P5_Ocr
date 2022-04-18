package com.cleanup.todoc.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.cleanup.todoc.model.Project;

@Dao
public interface ProjectDao {

    @Query("SELECT * FROM project_table")
    int getAllProjects(Project PROJECT);

    @Insert
    void insert(Project project);

    @Query("DELETE FROM project_table")
    void deleteAll();
}