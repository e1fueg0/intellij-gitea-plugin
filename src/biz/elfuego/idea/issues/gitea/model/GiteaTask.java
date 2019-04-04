/*
 * Copyright © 2019 by elfuego.biz
 */
package biz.elfuego.idea.issues.gitea.model;

import biz.elfuego.idea.issues.gitea.util.Consts.TaskFields;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.http.util.TextUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static biz.elfuego.idea.issues.gitea.util.Utils.*;

/**
 * @author Roman Pedchenko <elfuego@elfuego.biz>
 * @date 2018.06.30
 */
public class GiteaTask {
    private String project;
    private String id;
    private String title;
    private String description;
    private Date createdAt;
    private Date updatedAt;
    private String state;
    private GiteaUser assignee;
    private List<GiteaUser> assignees;

    public GiteaTask(String project, JsonObject json) throws Exception {
        this.project = project;
        this.fromJson(json);
    }

    public String getProject() {
        return project;
    }

    @SuppressWarnings("unused")
    public void setProject(String project) {
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

    public void setState(String state) {
        this.state = state;
    }

    @SuppressWarnings("unused")
    public GiteaUser getAssignee() {
        return assignee;
    }

    @SuppressWarnings("WeakerAccess")
    public void setAssignee(GiteaUser assignee) {
        this.assignee = assignee;
    }

    public List<GiteaUser> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<GiteaUser> assignees) {
        this.assignees = assignees;
    }

    public boolean isValid() {
        return !(TextUtils.isEmpty(id) ||
                TextUtils.isEmpty(title) ||
                (createdAt == null) ||
                (updatedAt == null) ||
                TextUtils.isEmpty(state));
    }

    public boolean isAssignedTo(String login) {
        if (assignee != null && assignee.getLogin().equals(login))
            return true;
        if (assignees != null)
            for (GiteaUser u : assignees)
                if (u.getLogin().equals(login))
                    return true;
        return false;
    }

    private void fromJson(JsonObject current) throws Exception {
        this.setId(getString(current, TaskFields.NUMBER, ""));
        this.setTitle(getString(current, TaskFields.TITLE, ""));
        this.setDescription(getString(current, TaskFields.DESCRIPTION, ""));
        this.setCreatedAt(getDate(current, TaskFields.CREATEDAT));
        this.setUpdatedAt(getDate(current, TaskFields.UPDATEDAT));
        this.setState(getString(current, TaskFields.STATE, ""));
        if (current.has(TaskFields.ASSIGNEE) && current.get(TaskFields.ASSIGNEE).isJsonObject())
            this.setAssignee(new GiteaUser(getObject(current, TaskFields.ASSIGNEE)));
        if (current.has(TaskFields.ASSIGNEES) && current.get(TaskFields.ASSIGNEES).isJsonArray()) {
            JsonArray arr = getArray(current.get(TaskFields.ASSIGNEES));
            List<GiteaUser> assignees1 = new ArrayList<>();
            for (JsonElement el : arr)
                if (el.isJsonObject())
                    assignees1.add(new GiteaUser(getObject(el)));
            this.setAssignees(assignees1);
        }
    }
}
