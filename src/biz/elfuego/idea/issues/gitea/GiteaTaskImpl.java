/*
 * Copyright © 2019 by elfuego.biz
 */
package biz.elfuego.idea.issues.gitea;

import biz.elfuego.idea.issues.gitea.model.GiteaTask;
import biz.elfuego.idea.issues.gitea.util.Consts;
import com.intellij.openapi.util.IconLoader;
import com.intellij.tasks.Comment;
import com.intellij.tasks.Task;
import com.intellij.tasks.TaskRepository;
import com.intellij.tasks.TaskType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Date;

/**
 * @author Roman Pedchenko <elfuego@elfuego.biz>
 * @date 2018.06.30
 */
public class GiteaTaskImpl extends Task implements Comparable<GiteaTaskImpl> {
    private final String project;
    private final GiteaRepository repository;
    private Comment[] comments;
    final GiteaTask task;

    GiteaTaskImpl(@NotNull GiteaRepository repository, @NotNull GiteaTask task) {
        this.repository = repository;
        this.task = task;
        this.project = task.getProject();
    }

    @NotNull
    @Override
    public String getId() {
        return task.getId();
    }

    @NotNull
    @Override
    public String getSummary() {
        return task.getTitle();
    }

    @Nullable
    @Override
    public String getDescription() {
        return task.getDescription();
    }

    @NotNull
    @Override
    public Comment[] getComments() {
        if (comments == null) {
            try {
                comments = repository.getComments(this);
            } catch(Exception ignored) {}
            if (comments == null)
                comments = new Comment[0];
        }
        return comments;
    }

    @NotNull
    @Override
    public Icon getIcon() {
        return IconLoader.getIcon("/resources/gitea.png");
    }

    @NotNull
    @Override
    public TaskType getType() {
        return TaskType.OTHER;
    }

    @Nullable
    @Override
    public Date getUpdated() {
        return task.getUpdatedAt();
    }

    @Nullable
    @Override
    public Date getCreated() {
        return task.getCreatedAt();
    }

    @Override
    public boolean isClosed() {
        return Consts.States.CLOSED.name().toLowerCase().equals(task.getState());
    }

    @Override
    public boolean isIssue() {
        return true;
    }

    @Nullable
    @Override
    public TaskRepository getRepository() {
        return repository;
    }

    @Nullable
    @Override
    public String getIssueUrl() {
        return repository.getUrl() + "/" + project + Consts.EndPoint.ISSUES + "/" + task.getId();
    }

    @Override
    public int compareTo(@NotNull GiteaTaskImpl o) {
        int me = Integer.parseInt(this.task.getId());
        int them = Integer.parseInt(o.task.getId());

        return Integer.compare(me, them);
    }
}
