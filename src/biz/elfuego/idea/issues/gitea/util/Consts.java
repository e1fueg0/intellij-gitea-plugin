/*
 * Copyright © 2018 by elfuego.biz
 */
package biz.elfuego.idea.issues.gitea.util;

/**
 * @author Roman Pedchenko <elfuego@elfuego.biz>
 * @date 2018.06.30
 */
public class Consts {
    public static final String ERROR = "Error communicating to the server";
    public static final String UNSPEC_PROJ_ID = "--";

    public interface Url {
        String DEFAULT = "https://try.gitea.io";
    }

    public interface EndPoint {
        String API = "/api/v1";
        String ME = "/user";
        String REPOS = "/repos/";
        String ISSUES = "/issues";
        String COMMENTS = "/comments";
        String REPOS_SEARCH = REPOS + "search?uid=";
    }

    public enum States {
        OPEN, CLOSED
    }

    public interface TaskFields {
        String ID = "id";
        String TITLE = "title";
        String DESCRIPTION = "body";
        String CREATEDAT = "created_at";
        String UPDATEDAT = "updated_at";
        String STATE = "state";
        String ASSIGNEE = "assignee";
    }

    public interface CommentFields {
        String DATE = "updated_at";
        String TEXT = "body";
        String USER = "user";
        String FULLNAME = "full_name";
        String USERNAME = "username";
    }
}
