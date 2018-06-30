/*
 * Copyright © 2018 by elfuego.biz
 */
package biz.elfuego.idea.issues.gitea.model;

import biz.elfuego.idea.issues.gitea.util.Consts.TaskFields;
import com.google.gson.JsonObject;
import org.apache.http.util.TextUtils;

import java.util.Date;

import static biz.elfuego.idea.issues.gitea.util.Utils.getDate;
import static biz.elfuego.idea.issues.gitea.util.Utils.getString;

/**
 * @author Roman Pedchenko <elfuego@elfuego.biz>
 * @date 2018.06.30
 */
public class GiteaTask {
    private GiteaProject project;
    private String id;
    private String title;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private String state;
    private String assignee;

    public GiteaTask(GiteaProject project, JsonObject json) {
        this.project = project;
        this.fromJson(json);
    }

    public GiteaProject getProject() {
        return project;
    }

    public void setProject(GiteaProject project) {
        this.project = project;
    }

    public String getId() {
        return id;
    }

    @SuppressWarnings("WeakerAccess")
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    @SuppressWarnings("WeakerAccess")
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    @SuppressWarnings("WeakerAccess")
    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @SuppressWarnings("WeakerAccess")
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    @SuppressWarnings("WeakerAccess")
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getState() {
        return state;
    }

    @SuppressWarnings("WeakerAccess")
    public void setState(String state) {
        this.state = state;
    }

    public String getAssignee() {
        return assignee;
    }

    @SuppressWarnings("WeakerAccess")
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public boolean isValid() {
        return !(TextUtils.isEmpty(id) ||
                TextUtils.isEmpty(title) ||
                (createdAt == null) ||
                (updatedAt == null) ||
                TextUtils.isEmpty(state));
    }

    private void fromJson(JsonObject current) {
        if (current.has(TaskFields.ID)) {
            this.setId(getString(current, TaskFields.ID, ""));
        }
        if (current.has(TaskFields.TITLE)) {
            this.setTitle(getString(current, TaskFields.TITLE, ""));
        }
        if (current.has(TaskFields.DESCRIPTION)) {
            this.setDescription(getString(current, TaskFields.DESCRIPTION, ""));
        }
        if (current.has(TaskFields.CREATEDAT)) {
            this.setCreatedAt(getDate(current, TaskFields.CREATEDAT));
        }
        if (current.has(TaskFields.UPDATEDAT)) {
            this.setUpdatedAt(getDate(current, TaskFields.UPDATEDAT));
        }
        if (current.has(TaskFields.STATE)) {
            this.setState(getString(current, TaskFields.STATE, ""));
        }
        if (current.has(TaskFields.ASSIGNEE)) {
            this.setAssignee(getString(current, TaskFields.ASSIGNEE, ""));
        }
    }
}
