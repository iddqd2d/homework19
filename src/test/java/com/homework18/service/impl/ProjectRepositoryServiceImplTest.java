package com.homework18.service.impl;

import com.homework18.config.ApplicationConfig;
import com.homework18.model.Project;
import com.homework18.service.ProjectRepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class ProjectRepositoryServiceImplTest {
    @Autowired
    private ProjectRepositoryService service;

    @Test
    public void createProject() {
        Project project = new Project()
                .setName("New");
        service.createProject(project);
        assertEquals(service.getProjectById(project.getId()), project);
    }

    @Test
    public void getProjectById() {
        Project project = new Project()
                .setName("New");
        service.createProject(project);
        assertEquals(service.getProjectById(project.getId()), project);
    }

    @Test
    public void updateProject() {
        Project project = new Project()
                .setName("Name");
        service.createProject(project);
        project.setName("Updated");
        service.updateProject(project);
        assertEquals(project, service.getProjectById(1));
    }

    @Test
    public void deleteProjectById() {
        Project project = new Project()
                .setName("Dell");
        service.createProject(project);
        service.deleteProjectById(1);
        assertNotEquals(service.getProjectById(1), project);
    }

    @Test
    public void getAllMoreThanBalance() {
        Project project = new Project()
                .setName("New").setBalance(15000);
        service.createProject(project);
        assertEquals(service.getAllMoreThanBalance(11000).size(), 1);
    }
}
