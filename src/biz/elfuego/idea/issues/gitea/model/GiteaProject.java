/*
 * Copyright © 2018 by elfuego.biz
 */
package biz.elfuego.idea.issues.gitea.model;

import biz.elfuego.idea.issues.gitea.util.Consts;

/**
 * @author Roman Pedchenko <elfuego@elfuego.biz>
 * @date 2018.06.30
 */
public class GiteaProject {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public GiteaProject setId(String mProjectId) {
        this.id = mProjectId;
        return this;
    }

    public String getName() {
        return name;
    }

    public GiteaProject setName(String mProjectTitle) {
        this.name = mProjectTitle;
        return this;
    }

    public boolean isValid() {
        return !(id.equals("") || name.equals(""));
    }

    @Override
    public String toString() {
        return name != null ? name : super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        GiteaProject that = (GiteaProject) o;

        return (id != null ? id.equals(that.id) : that.id == null) && (name != null ? name.equals(that.name) : that.name == null);
    }

    public static final GiteaProject UNSPECIFIED_PROJECT = new GiteaProject() {
        @Override
        public String getName() {
            return "-- Select A Project (Required) --";
        }

        @Override
        public String getId() {
            return Consts.UNSPEC_PROJ_ID;
        }

        @Override
        public String toString() {
            return getName();
        }
    };
}
