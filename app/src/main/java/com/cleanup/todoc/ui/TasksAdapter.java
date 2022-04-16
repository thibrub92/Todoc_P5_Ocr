package com.cleanup.todoc.ui;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.cleanup.todoc.R;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.SortMethod;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.model.TaskWithProject;
import java.util.Collections;
import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TaskViewHolder> {

    @NonNull
    private List<TaskWithProject> tasks;

    @NonNull
    private final DeleteTaskListener deleteTaskListener;

    TasksAdapter(@NonNull final List<TaskWithProject> tasks, @NonNull final DeleteTaskListener deleteTaskListener) {
        this.tasks = tasks;
        this.deleteTaskListener = deleteTaskListener;
    }

    void updateTasks(@NonNull final List<TaskWithProject> tasks, SortMethod sortMethod) {
        sortTasks(sortMethod);
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    void sortTasks(SortMethod sortMethod) {
        switch (sortMethod) {
            case ALPHABETICAL:
                Collections.sort(tasks, new TaskWithProject.TaskAZComparator());
                break;
            case ALPHABETICAL_INVERTED:
                Collections.sort(tasks, new TaskWithProject.TaskZAComparator());
                break;
            case RECENT_FIRST:
                Collections.sort(tasks, new TaskWithProject.TaskRecentComparator());
                break;
            case OLD_FIRST:
                Collections.sort(tasks, new TaskWithProject.TaskOldComparator());
                break;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_task, viewGroup, false);
        return new TaskViewHolder(view, deleteTaskListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder taskViewHolder, int position) {
        taskViewHolder.bind(tasks.get(position));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public interface DeleteTaskListener {

        void onDeleteTask(Task task);
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {

        private final AppCompatImageView imgProject;

        private final TextView lblTaskName;

        private final TextView lblProjectName;

        private final AppCompatImageView imgDelete;

        private final DeleteTaskListener deleteTaskListener;

        TaskViewHolder(@NonNull View itemView, @NonNull DeleteTaskListener deleteTaskListener) {
            super(itemView);

            this.deleteTaskListener = deleteTaskListener;

            imgProject = itemView.findViewById(R.id.img_project);
            lblTaskName = itemView.findViewById(R.id.lbl_task_name);
            lblProjectName = itemView.findViewById(R.id.lbl_project_name);
            imgDelete = itemView.findViewById(R.id.img_delete);

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Object tag = view.getTag();
                    if (tag instanceof TaskWithProject) {
                        TaskViewHolder.this.deleteTaskListener.onDeleteTask((Task) tag);
                    }
                }
            });
        }

        void bind(TaskWithProject taskWithProject) {
            lblTaskName.setText(taskWithProject.task.getName());
            imgDelete.setTag(taskWithProject.task);

            final Project taskProject = taskWithProject.project;
            if (taskProject != null) {
                imgProject.setSupportImageTintList(ColorStateList.valueOf(taskProject.getColor()));
                lblProjectName.setText(taskProject.getName());
            } else {
                imgProject.setVisibility(View.INVISIBLE);
                lblProjectName.setText("");
            }


        }
    }
}
