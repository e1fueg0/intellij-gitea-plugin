/*
 * Copyright © 2019 by elfuego.biz
 */
package biz.elfuego.idea.issues.gitea.model;

import biz.elfuego.idea.issues.gitea.util.Consts;
import com.google.gson.JsonObject;

import static biz.elfuego.idea.issues.gitea.util.Utils.getString;

/**
 * @author Roman Pedchenko <elfuego@elfuego.biz>
 * @date 2019.04.04
 */
public class GiteaUser {
    private String id;
    private String login;
    private String username;
    private String full_name;
    private String email;
    private String avatar_url;
    private String language;

    public GiteaUser(JsonObject json) {
        this.fromJson(json);
    }

    public String getId() {
        return id;
    }

    @SuppressWarnings("WeakerAccess")
    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    @SuppressWarnings("WeakerAccess")
    public void setLogin(String login) {
        this.login = login;
    }

    public String getUsername() {
        return username;
    }

    @SuppressWarnings("WeakerAccess")
    public void setUsername(String username) {
        this.username = username;
    }

    public String getFull_name() {
        return full_name;
    }

    @SuppressWarnings("WeakerAccess")
    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    @SuppressWarnings("WeakerAccess")
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    @SuppressWarnings("WeakerAccess")
    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getLanguage() {
        return language;
    }

    @SuppressWarnings("WeakerAccess")
    public void setLanguage(String language) {
        this.language = language;
    }

    public String getName() {
        if (!full_name.isEmpty())
            return full_name;
        if (!username.isEmpty())
            return username;
        return login;
    }

    private void fromJson(JsonObject current) {
        this.setId(getString(current, Consts.UserFields.ID, ""));
        this.setLogin(getString(current, Consts.UserFields.LOGIN, ""));
        this.setUsername(getString(current, Consts.UserFields.USERNAME, ""));
        this.setFull_name(getString(current, Consts.UserFields.FULL_NAME, ""));
        this.setEmail(getString(current, Consts.UserFields.EMAIL, ""));
        this.setAvatar_url(getString(current, Consts.UserFields.AVATAR_URL, ""));
        this.setLanguage(getString(current, Consts.UserFields.LANGUAGE, ""));
    }
}
