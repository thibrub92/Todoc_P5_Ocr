package com.cleanup.todoc;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.model.TaskWithProject;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

/**
 * Unit tests for tasks
 *
 * @author Gaëtan HERFRAY
 */
public class TaskUnitTest {
    @Test
    public void test_projects() {
        final Task task1 = new Task(1, "task 1", new Date().getTime());
        final Task task2 = new Task(2, "task 2", new Date().getTime());
        final Task task3 = new Task(3, "task 3", new Date().getTime());
        final Task task4 = new Task(4, "task 4", new Date().getTime());

        assertEquals("Projet Tartampion", task1.getProjectId());
        assertEquals("Projet Lucidia", task2.getProjectId());
        assertEquals("Projet Circus", task3.getProjectId());
        assertNull(task4.getProjectId());
    }

    @Test
    public void test_az_comparator() {

        // Projet Test
        Project testProject = new Project(1L, "Projet Tartampion", 0xC61847);
        Project testProject2 = new Project(2L, "Projet Lucidia", 0xCB55E9);
        Project testProject3 = new Project(3L, "Projet Circus", 0x1693B2);

        // Task Test
        Task testTask = new Task(1, "aaa", 123);
        Task testTask2 = new Task(1, "bbb", 123);
        Task testTask3 = new Task(1, "ccc", 123);

        // Create Task with Project
        TaskWithProject taskWithProject = new TaskWithProject();
        taskWithProject.setProject(testProject);
        taskWithProject.setTask(testTask);
        taskWithProject.setTask(testTask2);
        taskWithProject.setTask(testTask3);

        TaskWithProject taskWithProject2 = new TaskWithProject();
        taskWithProject.setProject(testProject);
        taskWithProject.setTask(testTask);
        taskWithProject.setTask(testTask2);
        taskWithProject.setTask(testTask3);

        TaskWithProject taskWithProject3 = new TaskWithProject();
        taskWithProject.setProject(testProject);
        taskWithProject.setProject(testProject2);
        taskWithProject.setProject(testProject3);
        taskWithProject.setTask(testTask);
        taskWithProject.setTask(testTask2);


        final ArrayList<TaskWithProject> tasks = new ArrayList<>();
        tasks.add(taskWithProject);
        tasks.add(taskWithProject2);
        tasks.add(taskWithProject3);
        Collections.sort(tasks, new TaskWithProject.TaskAZComparator());

        assertSame(tasks.get(0), taskWithProject);
        assertSame(tasks.get(1), taskWithProject2);
        assertSame(tasks.get(2), taskWithProject3);
    }

    @Test
    public void test_za_comparator() {

        // Projet Test
        Project testProject = new Project(1L, "Projet Tartampion", 0xC61847);
        Project testProject2 = new Project(2L, "Projet Lucidia", 0xCB55E9);
        Project testProject3 = new Project(3L, "Projet Circus", 0x1693B2);

        // Task Test
        Task testTask = new Task(1, "aaa", 123);
        Task testTask2 = new Task(1, "bbb", 123);
        Task testTask3 = new Task(1, "ccc", 123);

        // Create Task with Project
        TaskWithProject taskWithProject = new TaskWithProject();
        taskWithProject.setProject(testProject);
        taskWithProject.setTask(testTask);
        taskWithProject.setTask(testTask2);
        taskWithProject.setTask(testTask3);

        TaskWithProject taskWithProject2 = new TaskWithProject();
        taskWithProject.setProject(testProject);
        taskWithProject.setTask(testTask);
        taskWithProject.setTask(testTask2);
        taskWithProject.setTask(testTask3);

        TaskWithProject taskWithProject3 = new TaskWithProject();
        taskWithProject.setProject(testProject);
        taskWithProject.setProject(testProject2);
        taskWithProject.setProject(testProject3);
        taskWithProject.setTask(testTask);
        taskWithProject.setTask(testTask2);


        final ArrayList<TaskWithProject> tasks = new ArrayList<>();
        tasks.add(taskWithProject);
        tasks.add(taskWithProject2);
        tasks.add(taskWithProject3);
        Collections.sort(tasks, new TaskWithProject.TaskZAComparator());

        assertSame(tasks.get(0), taskWithProject);
        assertSame(tasks.get(1), taskWithProject2);
        assertSame(tasks.get(2), taskWithProject3);
    }

    @Test
    public void test_recent_comparator() {
        // Projet Test
        Project testProject = new Project(1L, "Projet Tartampion", 0xC61847);
        Project testProject2 = new Project(2L, "Projet Lucidia", 0xCB55E9);
        Project testProject3 = new Project(3L, "Projet Circus", 0x1693B2);

        // Task Test
        Task testTask = new Task(1, "aaa", 123);
        Task testTask2 = new Task(1, "bbb", 123);
        Task testTask3 = new Task(1, "ccc", 123);

        // Create Task with Project
        TaskWithProject taskWithProject = new TaskWithProject();
        taskWithProject.setProject(testProject);
        taskWithProject.setTask(testTask);
        taskWithProject.setTask(testTask2);
        taskWithProject.setTask(testTask3);

        TaskWithProject taskWithProject2 = new TaskWithProject();
        taskWithProject.setProject(testProject);
        taskWithProject.setTask(testTask);
        taskWithProject.setTask(testTask2);
        taskWithProject.setTask(testTask3);

        TaskWithProject taskWithProject3 = new TaskWithProject();
        taskWithProject.setProject(testProject);
        taskWithProject.setProject(testProject2);
        taskWithProject.setProject(testProject3);
        taskWithProject.setTask(testTask);
        taskWithProject.setTask(testTask2);


        final ArrayList<TaskWithProject> tasks = new ArrayList<>();
        tasks.add(taskWithProject);
        tasks.add(taskWithProject2);
        tasks.add(taskWithProject3);
        Collections.sort(tasks, new TaskWithProject.TaskRecentComparator());

        assertSame(tasks.get(0), taskWithProject);
        assertSame(tasks.get(1), taskWithProject2);
        assertSame(tasks.get(2), taskWithProject3);
    }

    @Test
    public void test_old_comparator() {
        // Projet Test
        Project testProject = new Project(1L, "Projet Tartampion", 0xC61847);
        Project testProject2 = new Project(2L, "Projet Lucidia", 0xCB55E9);
        Project testProject3 = new Project(3L, "Projet Circus", 0x1693B2);

        // Task Test
        Task testTask = new Task(1, "aaa", 123);
        Task testTask2 = new Task(1, "bbb", 123);
        Task testTask3 = new Task(1, "ccc", 123);

        // Create Task with Project
        TaskWithProject taskWithProject = new TaskWithProject();
        taskWithProject.setProject(testProject);
        taskWithProject.setTask(testTask);
        taskWithProject.setTask(testTask2);
        taskWithProject.setTask(testTask3);

        TaskWithProject taskWithProject2 = new TaskWithProject();
        taskWithProject.setProject(testProject);
        taskWithProject.setTask(testTask);
        taskWithProject.setTask(testTask2);
        taskWithProject.setTask(testTask3);

        TaskWithProject taskWithProject3 = new TaskWithProject();
        taskWithProject.setProject(testProject);
        taskWithProject.setProject(testProject2);
        taskWithProject.setProject(testProject3);
        taskWithProject.setTask(testTask);
        taskWithProject.setTask(testTask2);


        final ArrayList<TaskWithProject> tasks = new ArrayList<>();
        tasks.add(taskWithProject);
        tasks.add(taskWithProject2);
        tasks.add(taskWithProject3);
        Collections.sort(tasks, new TaskWithProject.TaskOldComparator());

        assertSame(tasks.get(0), taskWithProject);
        assertSame(tasks.get(1), taskWithProject2);
        assertSame(tasks.get(2), taskWithProject3);
    }
}