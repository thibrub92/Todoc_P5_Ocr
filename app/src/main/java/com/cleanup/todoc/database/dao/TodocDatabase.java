package com.cleanup.todoc.database.dao;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Task.class, Project.class},
        version = 1, exportSchema = false)

public abstract class TodocDatabase extends RoomDatabase {

    // --- SINGLETON ---
    public static volatile TodocDatabase INSTANCE;
    public static final ExecutorService databaseWriteExecutor = null;

    // --- DAO ---
    public abstract TaskDao taskDao();

    public abstract ProjectDao projectDao();

    // --- INSTANCE ---
    public static TodocDatabase getINSTANCE(final Context context) {
        if (INSTANCE == null) {
            synchronized (TodocDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TodocDatabase.class, "todoc_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(taskDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final Callback taskDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Executors.newSingleThreadExecutor().execute(() -> {

                ProjectDao projectDao = INSTANCE.projectDao();
                projectDao.deleteAll();
                Log.d(TAG, "onCreate: delete");

                Project project1 = new Project(1L, "Projet Tartampion", 0xFFEADAD1);
                Project project2 = new Project(2L, "Projet Lucidia", 0xFFB4CDBA);
                Project project3 = new Project(3L, "Projet Circus", 0xFFA3CED2);
                projectDao.insert(project1);
                projectDao.insert(project2);
                projectDao.insert(project3);
            });
        }
    };
}
