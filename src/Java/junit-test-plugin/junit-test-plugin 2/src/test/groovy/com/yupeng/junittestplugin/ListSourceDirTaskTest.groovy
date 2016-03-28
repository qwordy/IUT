package com.yupeng.junittestplugin

import org.junit.Test
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.api.Project
import static org.junit.Assert.*

class ListSourceDirTaskTest {
    @Test
    public void canAddTaskToProject() {
        Project project = ProjectBuilder.builder().build()
        def task = project.task('listSrcDirs', type: ListSourceDirTask)
        assertTrue(task instanceof ListSourceDirTask)
    }
}
