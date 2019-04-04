/*
 * Copyright © 2019 by elfuego.biz
 */
package biz.elfuego.idea.issues.gitea.util;

/**
 * @author Roman Pedchenko <elfuego@elfuego.biz>
 * @date 2018.06.30
 */
public class Consts {
    public static final String ERROR = "Error communicating to the server";
    public static final String UNSPEC_PROJ_ID = "--";

    public enum ProjectFilter {
        GENERAL("General search"),
        CONTRUBUTOR("Owned and participated by me"),
        OWNER("Owned by me");

        private final String message;

        ProjectFilter(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return this.message;
        }
    }

    public interface Url {
        String DEFAULT = "https://try.gitea.io";
    }

    public interface EndPoint {
        String API = "/api/v1";
        String ME = "/user";
        String REPOS = "/repos/";
        String ISSUES = "/issues";
        String COMMENTS = "/comments";
        String REPOS_SEARCH = REPOS + "search";
        String REPOS_SEARCH_UID = REPOS + "search?uid=";
        String REPOS_SEARCH_UID_EX = REPOS + "search?exclusive=true&uid=";
    }

    public enum States {
        OPEN, CLOSED
    }

    public interface TaskFields {
        String NUMBER = "number";
        String TITLE = "title";
        String DESCRIPTION = "body";
        String CREATEDAT = "created_at";
        String UPDATEDAT = "updated_at";
        String STATE = "state";
        String ASSIGNEE = "assignee";
        String ASSIGNEES = "assignees";
    }

    public interface CommentFields {
        String DATE = "updated_at";
        String TEXT = "body";
        String USER = "user";
    }

    public interface UserFields {
        String ID = "id";
        String LOGIN = "login";
        String FULL_NAME = "full_name";
        String EMAIL = "email";
        String AVATAR_URL = "avatar_url";
        String LANGUAGE = "language";
        String USERNAME = "username";
    }
}
