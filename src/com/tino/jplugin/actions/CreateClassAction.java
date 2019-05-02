package com.tino.jplugin.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.tino.jplugin.uis.CreateClassDialog;

public class CreateClassAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        CreateClassDialog dialog = new CreateClassDialog(e.getProject());
        dialog.pack();
        dialog.setVisible(true);
    }
}