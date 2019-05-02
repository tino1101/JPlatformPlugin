package com.tino.jplugin.utils;

import com.intellij.execution.executors.DefaultRunExecutor;
import com.intellij.openapi.externalSystem.model.ProjectSystemId;
import com.intellij.openapi.externalSystem.model.execution.ExternalSystemTaskExecutionSettings;
import com.intellij.openapi.externalSystem.service.execution.ProgressExecutionMode;
import com.intellij.openapi.externalSystem.task.TaskCallback;
import com.intellij.openapi.externalSystem.util.ExternalSystemUtil;
import com.intellij.openapi.project.Project;

import java.util.List;
import java.util.Map;

public class GradleUtil {

    public static void runTask(Project project, List<String> tasks, ProgressExecutionMode progressExecutionMode, Boolean activateToolWindowBeforeRun, Map<String, String> env) {

        ProjectSystemId GRADLE = new ProjectSystemId("GRADLE");

        ExternalSystemTaskExecutionSettings settings = new ExternalSystemTaskExecutionSettings();
        settings.setExecutionName("Running Task:$tasks");
        settings.setTaskNames(tasks);
        settings.setExternalSystemIdString(GRADLE.getId());
        settings.setExternalProjectPath(project.getBasePath());
        settings.setVmOptions(getVmOptions(env));
        ExternalSystemUtil.runTask(settings, DefaultRunExecutor.EXECUTOR_ID, project, GRADLE, new TaskCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFailure() {

            }
        }, progressExecutionMode, activateToolWindowBeforeRun);

    }

    private static String getVmOptions(Map<String, String> env) {
        StringBuilder option = new StringBuilder();
        env.forEach((k, v) -> {
            if (!k.isEmpty() && !v.isEmpty()) {
                option.append("-Dorg.gradle.project." + k + "=" + v + " ");
            }
        });
        return option.toString();
    }
}