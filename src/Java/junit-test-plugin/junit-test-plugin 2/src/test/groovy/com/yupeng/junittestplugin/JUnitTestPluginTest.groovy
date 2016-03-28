package com.yupeng.junittestplugin

import org.junit.Test
import org.gradle.testfixtures.ProjectBuilder
import org.gradle.api.Project
import static org.junit.Assert.*

class JUnitTestPluginTest {
    @Test
    public void jUnitTestPluginAddsGreetingTaskToProject() {
        Project project = ProjectBuilder.builder().build()
        project.pluginManager.apply 'com.yupeng.junit-test-plugin'

        assertTrue(project.tasks.hello instanceof GreetingTask)
    }
}
