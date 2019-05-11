package com.homework18.service;

import com.homework18.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepositoryService {

    void createProject(Project user);

    Optional<Project> getProjectById(int id);

    void updateProject(Project project);

    void deleteProjectById(int id);

    List<Project> getAllMoreThanBalance(Integer balance);

    List<Project> getAllProject();
}
