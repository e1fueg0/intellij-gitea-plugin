/*
 * Copyright © 2019 by elfuego.biz
 */
package biz.elfuego.idea.issues.gitea;

import com.intellij.openapi.project.Project;
import com.intellij.tasks.config.BaseRepositoryEditor;
import com.intellij.ui.components.JBLabel;
import com.intellij.util.Consumer;
import com.intellij.util.ui.FormBuilder;
import org.jdesktop.swingx.HorizontalLayout;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * @author Roman Pedchenko <elfuego@elfuego.biz>
 * @date 2018.06.30
 */
public class GiteaRepositoryEditor extends BaseRepositoryEditor<GiteaRepository> {
    private JBLabel repoLabel;
    private JTextField repoName;

    private JTextField projName;

    private JBLabel tokenLabel;
    private JPasswordField token;

    private JBLabel assignedLabel;
    private JCheckBox assignedCheckBox;

    GiteaRepositoryEditor(GiteaRepository repository, Project project, Consumer<GiteaRepository> consumer) {
        super(project, repository, consumer);

        repoName.setText(repository.getRepoName());
        projName.setText(repository.getProjName());
        token.setText(repository.getToken());
        assignedCheckBox.setSelected(repository.getAssigned());
        installListener(repoName);
        installListener(projName);
        installListener(token);
        installListener(assignedCheckBox);

        myUserNameText.setVisible(false);
        myPasswordText.setVisible(false);
        myUserNameText.setEnabled(false);
        myPasswordText.setEnabled(false);
        myUsernameLabel.setVisible(false);
        myPasswordLabel.setVisible(false);
    }

    @Nullable
    @Override
    protected JComponent createCustomPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new HorizontalLayout());
        repoName = new JTextField(20);
        repoLabel = new JBLabel("Repository:", SwingConstants.RIGHT);
        repoLabel.setLabelFor(panel);
        panel.add(repoName);

        projName = new JTextField(30);
        JBLabel projLabel = new JBLabel(" / Project: ", SwingConstants.RIGHT);
        projLabel.setLabelFor(projName);
        panel.add(projLabel);
        panel.add(projName);

        token = new JPasswordField();
        tokenLabel = new JBLabel("Token:", SwingConstants.RIGHT);
        tokenLabel.setLabelFor(token);

        assignedCheckBox = new JCheckBox();
        assignedLabel = new JBLabel("Only issues assigned to me:", SwingConstants.RIGHT);
        assignedLabel.setLabelFor(assignedCheckBox);

        return new FormBuilder().setAlignLabelOnRight(true)
                .addLabeledComponent(repoLabel, panel)
                .addLabeledComponent(tokenLabel, token)
                .addLabeledComponent(assignedLabel, assignedCheckBox)
                .getPanel();
    }

    @Override
    public void setAnchor(@Nullable JComponent anchor) {
        super.setAnchor(anchor);
        repoLabel.setAnchor(anchor);
        tokenLabel.setAnchor(anchor);
//        assignedLabel.setAnchor(anchor);
    }

    @Override
    public void apply() {
        super.apply();
        myRepository.setRepoName(repoName.getText());
        myRepository.setProjName(projName.getText());
        //noinspection deprecation
        myRepository.setToken(token.getText());
        myRepository.setAssigned(assignedCheckBox.isSelected());
        myTestButton.setEnabled(myRepository.isConfigured());
    }
}
