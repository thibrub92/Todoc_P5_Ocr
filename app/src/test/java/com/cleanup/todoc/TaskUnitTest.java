package com.cleanup.todoc;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.model.TaskWithProject;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

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
    public void test_az_comparator() {

        // Projet Test
        Project testProject = new Project(1L, "Projet Tartampion", 0xC61847);
        Project testProject2 = new Project(2L, "Projet Lucidia", 0xCB55E9);

        // Task Test
        Task testTask = new Task(1L, "aaa", 1650626937);
        Task testTask2 = new Task(1L, "bbb", 1550626937);
        Task testTask3 = new Task(2L, "ccc", 1600626937);

        // Create Task with Project
        TaskWithProject taskWithProject = new TaskWithProject();
        taskWithProject.setProject(testProject);
        taskWithProject.setTask(testTask);

        TaskWithProject taskWithProject2 = new TaskWithProject();
        taskWithProject2.setProject(testProject);
        taskWithProject2.setTask(testTask2);

        TaskWithProject taskWithProject3 = new TaskWithProject();
        taskWithProject3.setProject(testProject2);
        taskWithProject3.setTask(testTask3);


        final List<TaskWithProject> tasks = new ArrayList<>();
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

        // Task Test
        Task testTask = new Task(1L, "aaa", 1650626937);
        Task testTask2 = new Task(1L, "bbb", 1550626937);
        Task testTask3 = new Task(2L, "ccc", 1600626937);

        // Create Task with Project
        TaskWithProject taskWithProject = new TaskWithProject();
        taskWithProject.setProject(testProject);
        taskWithProject.setTask(testTask);

        TaskWithProject taskWithProject2 = new TaskWithProject();
        taskWithProject2.setProject(testProject);
        taskWithProject2.setTask(testTask2);

        TaskWithProject taskWithProject3 = new TaskWithProject();
        taskWithProject3.setProject(testProject2);
        taskWithProject3.setTask(testTask3);


        final List<TaskWithProject> tasks = new ArrayList<>();
        tasks.add(taskWithProject);
        tasks.add(taskWithProject2);
        tasks.add(taskWithProject3);

        Collections.sort(tasks, new TaskWithProject.TaskZAComparator());

        assertSame(tasks.get(0), taskWithProject3);
        assertSame(tasks.get(1), taskWithProject2);
        assertSame(tasks.get(2), taskWithProject);
        ;
    }

    @Test
    public void test_recent_comparator() {
        // Projet Test
        Project testProject = new Project(1L, "Projet Tartampion", 0xC61847);
        Project testProject2 = new Project(2L, "Projet Lucidia", 0xCB55E9);

        // Task Test
        Task testTask = new Task(1L, "aaa", 1650626937);
        Task testTask2 = new Task(1L, "bbb", 1550626937);
        Task testTask3 = new Task(2L, "ccc", 1600626937);

        // Create Task with Project
        TaskWithProject taskWithProject = new TaskWithProject();
        taskWithProject.setProject(testProject);
        taskWithProject.setTask(testTask);

        TaskWithProject taskWithProject2 = new TaskWithProject();
        taskWithProject2.setProject(testProject);
        taskWithProject2.setTask(testTask2);

        TaskWithProject taskWithProject3 = new TaskWithProject();
        taskWithProject3.setProject(testProject2);
        taskWithProject3.setTask(testTask3);


        final List<TaskWithProject> tasks = new ArrayList<>();
        tasks.add(taskWithProject);
        tasks.add(taskWithProject2);
        tasks.add(taskWithProject3);

        Collections.sort(tasks, new TaskWithProject.TaskRecentComparator());

        assertSame(tasks.get(0), taskWithProject);
        assertSame(tasks.get(1), taskWithProject3);
        assertSame(tasks.get(2), taskWithProject2);
    }

    @Test
    public void test_old_comparator() {
        // Projet Test
        Project testProject = new Project(1L, "Projet Tartampion", 0xC61847);
        Project testProject2 = new Project(2L, "Projet Lucidia", 0xCB55E9);

        // Task Test
        Task testTask = new Task(1L, "aaa", 1650626937);
        Task testTask2 = new Task(1L, "bbb", 1550626937);
        Task testTask3 = new Task(2L, "ccc", 1600626937);

        // Create Task with Project
        TaskWithProject taskWithProject = new TaskWithProject();
        taskWithProject.setProject(testProject);
        taskWithProject.setTask(testTask);

        TaskWithProject taskWithProject2 = new TaskWithProject();
        taskWithProject2.setProject(testProject);
        taskWithProject2.setTask(testTask2);

        TaskWithProject taskWithProject3 = new TaskWithProject();
        taskWithProject3.setProject(testProject2);
        taskWithProject3.setTask(testTask3);


        final List<TaskWithProject> tasks = new ArrayList<>();
        tasks.add(taskWithProject);
        tasks.add(taskWithProject2);
        tasks.add(taskWithProject3);

        Collections.sort(tasks, new TaskWithProject.TaskOldComparator());

        assertSame(tasks.get(0), taskWithProject2);
        assertSame(tasks.get(1), taskWithProject3);
        assertSame(tasks.get(2), taskWithProject);
    }
}