package com.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.TaskCollection;

/**
*  Created by geng
*  on 2018/3/5.
*/

public class InsertPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        TaskCollection container = project.tasks;
        for (int i=0; i<container.size(); i++) {
            println("task:======  "+container.getAt(i).name)
        }
    }
}
