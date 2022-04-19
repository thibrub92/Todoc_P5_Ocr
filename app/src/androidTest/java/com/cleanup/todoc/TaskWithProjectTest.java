package com.cleanup.todoc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.InstrumentationRegistry;

import com.cleanup.todoc.database.dao.TodocDatabase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

public class TaskWithProjectTest {

    private TodocDatabase database;

    private static Project PROJECT = new Project(1, "Tartampion", 0xFFEADAD1);
    private static long PROJECT_ID = PROJECT.getId();
    private static Task TASK_FIRST = new Task(PROJECT_ID, "test1", 0);
    private static Task TASK_SECOND = new Task(PROJECT_ID, "test2", 0);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), TodocDatabase.class)
                .allowMainThreadQueries().build();
    }

    @After
    public void closeDb() throws Exception {
        this.database.close();
    }

    @Test
    public void insertOneProjectAndGetProjects() throws InterruptedException {
        List<Project> projects = (List<Project>) TestUtils.withRecyclerView(this.database.projectDao().getAllProjects(PROJECT));
        this.database.projectDao().getAllProjects(PROJECT);
        assertEquals(projects.size(), 0);
        projects = TestUtils.withRecyclerView(this.database.projectDao().getAllProjects(PROJECT));
        assertEquals(projects.size(), 1);

    }

    @Test
    public void getTasksWhenNoTaskInserted() throws InterruptedException {
        List<Task> tasks = TestUtils.withRecyclerView(this.database.taskDao().getAllTasks());
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void insertOneTaskAndGetTasks() throws InterruptedException {
        List<Task> tasks = TestUtils.withRecyclerView(this.database.taskDao().getAllTasks());
        assertEquals(tasks.size(), 0);

        this.database.projectDao().insert(PROJECT);
        this.database.taskDao().insert(TASK_FIRST);

        tasks = (List<Task>) TestUtils.withRecyclerView(this.database.taskDao().getAllTasks());
        assertEquals(tasks.size(), 1);
    }

    @Test
    public void insertTasksAndDeleteTheSecond() throws InterruptedException {
        this.database.projectDao().insert(PROJECT);
        this.database.taskDao().insert(TASK_FIRST);
        this.database.taskDao().insert(TASK_SECOND);
        List<Task> tasks = TestUtils.withRecyclerView(this.database.taskDao().getAllTasks());
        assertEquals(tasks.size(), 2);
        this.database.taskDao().deleteTask(TASK_SECOND);
        tasks = TestUtils.withRecyclerView(this.database.taskDao().getAllTasks());
        assertEquals(tasks.size(), 1);
    }
}
