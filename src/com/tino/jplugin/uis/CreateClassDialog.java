package com.tino.jplugin.uis;

import com.intellij.openapi.externalSystem.service.execution.ProgressExecutionMode;
import com.intellij.openapi.project.Project;
import com.tino.jplugin.utils.GradleUtil;
import com.tino.jplugin.utils.UiUtil;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateClassDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;

    private Project project;

    public CreateClassDialog(Project project) {
        this.project = project;
        UiUtil.centerDialog(this, 600, 200);
        setLocationRelativeTo(null);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        List<String> tasks = new ArrayList<>();
        tasks.add("createClassFromPlatform");
        Map<String, String> env = new HashMap<>();
        env.put("packageName", textField1.getText());
        env.put("className", textField2.getText());
        env.put("property", textField3.getText());
        GradleUtil.runTask(project, tasks, ProgressExecutionMode.IN_BACKGROUND_ASYNC, true, env);
        dispose();
    }

    private void onCancel() {
        dispose();
    }
}