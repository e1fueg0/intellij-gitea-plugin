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
        String DEFAULT = "https://git2.elfuego.biz:444";
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
        OPEN, CLOSED;
    }

    public interface TaskFields {
        static final String ID = "id";
        static final String TITLE = "title";
        static final String DESCRIPTION = "body";
        static final String CREATEDAT = "created_at";
        static final String UPDATEDAT = "updated_at";
        static final String STATE = "state";
        static final String ASSIGNEE = "assignee";
    }

    public interface CommentFields {
        static final String DATE = "updated_at";
        static final String TEXT = "body";
        static final String USER = "user";
        static final String FULLNAME = "full_name";
        static final String USERNAME = "username";
    }
}
