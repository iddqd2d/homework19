package com.homework18.service;

import com.homework18.model.Project;

import java.util.List;

public interface ProjectRepositoryService {

    void createProject(Project project);

    Project getProjectById(int id);

    void updateProject(Project project);

    void deleteProjectById(int id);

    List<Project> getAllMoreThanBalance(Integer balance);

    List<Project> getAllProject();
}
