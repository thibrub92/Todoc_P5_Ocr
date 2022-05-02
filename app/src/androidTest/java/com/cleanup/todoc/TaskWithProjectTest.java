package com.cleanup.todoc;

import static org.junit.Assert.assertEquals;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;

import com.cleanup.todoc.database.dao.TodocDatabase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.model.TaskWithProject;
import com.cleanup.todoc.utils.LiveDataTestUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

public class TaskWithProjectTest {

    private TodocDatabase database;

    private static final long PROJECT_ID = 1L;
    private static final Project PROJECT = new Project(PROJECT_ID, "Tartampion", 0xFFEADAD1);
    private static final Task TASK_FIRST = new Task(PROJECT_ID, "aaa", 1650626937);
    private static final Task TASK_SECOND = new Task(PROJECT_ID, "bbb", 1550626937);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        this.database = Room
                .inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), TodocDatabase.class)
                .allowMainThreadQueries().build();
        TASK_FIRST.setId(1L);
        TASK_SECOND.setId(2L);
    }

    @After
    public void closeDb() throws Exception {
        this.database.close();
    }

    @Test
    public void insertOneProjectAndGetProjects() throws InterruptedException {

        List<Project> projects = LiveDataTestUtil.getValue(this.database.projectDao().getAllProjects());
        assertEquals(projects.size(), 0);


        this.database.projectDao().insert(new Project(6L, "test", 0xFFEADAD1));

        projects = LiveDataTestUtil.getValue(this.database.projectDao().getAllProjects());
        assertEquals(projects.size(), 1);
    }

    @Test
    public void insertOneTaskAndGetTasks() throws InterruptedException {
        List<TaskWithProject> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getAllTasks());
        assertEquals(tasks.size(), 0);

        this.database.projectDao().insert(PROJECT);
        this.database.taskDao().insert(TASK_FIRST);

        tasks = LiveDataTestUtil.getValue(this.database.taskDao().getAllTasks());
        assertEquals(tasks.size(), 1);
    }

    @Test
    public void insertTasksAndDeleteTheSecond() throws InterruptedException {
        this.database.projectDao().insert(PROJECT);
        this.database.taskDao().insert(TASK_FIRST);
        this.database.taskDao().insert(TASK_SECOND);

        List<TaskWithProject> tasks = LiveDataTestUtil.getValue(this.database.taskDao().getAllTasks());
        assertEquals(2, tasks.size());
        this.database.taskDao().deleteTask(TASK_SECOND);

        tasks = LiveDataTestUtil.getValue(this.database.taskDao().getAllTasks());
        assertEquals(1, tasks.size());
    }
}
