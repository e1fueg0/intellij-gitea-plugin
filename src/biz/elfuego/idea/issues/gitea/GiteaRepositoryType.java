/*
 * Copyright © 2018 by elfuego.biz
 */
package biz.elfuego.idea.issues.gitea;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.IconLoader;
import com.intellij.tasks.TaskRepository;
import com.intellij.tasks.config.TaskRepositoryEditor;
import com.intellij.tasks.impl.BaseRepositoryType;
import com.intellij.util.Consumer;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author Roman Pedchenko <elfuego@elfuego.biz>
 * @date 2018.06.30
 */
public class GiteaRepositoryType extends BaseRepositoryType<GiteaRepository> {
    @NotNull
    @Override
    public String getName() {
        return "Gitea";
    }

    @NotNull
    @Override
    public Icon getIcon() {
        return IconLoader.getIcon("/resources/gitea.png");
    }

    @NotNull
    @Override
    public TaskRepositoryEditor createEditor(GiteaRepository repository, Project project, Consumer<GiteaRepository> consumer) {
        return new GiteaRepositoryEditor(repository, project, consumer);
    }

    @NotNull
    @Override
    public TaskRepository createRepository() {
        return new GiteaRepository(this);
    }

    @Override
    public Class<GiteaRepository> getRepositoryClass() {
        return GiteaRepository.class;
    }
}
