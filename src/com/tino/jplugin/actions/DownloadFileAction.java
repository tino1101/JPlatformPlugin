package com.tino.jplugin.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.tino.jplugin.uis.DownloadFileDialog;

public class DownloadFileAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        DownloadFileDialog dialog = new DownloadFileDialog(e.getProject());
        dialog.pack();
        dialog.setVisible(true);
    }
}