package com.homework18.service.impl;

import com.homework18.model.Project;
import com.homework18.repository.ProjectRepository;
import com.homework18.service.ProjectRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProjectRepositoryServiceImpl implements ProjectRepositoryService {
    private final ProjectRepository repository;

    @Override
    public void createProject(Project project) {
        repository.save(project);
    }

    @Override
    public Optional<Project> getProjectById(int id) {
        return repository.findById(id);
    }

    @Override
    public void updateProject(Project project) {
        repository.save(project);
    }

    @Override
    public void deleteProjectById(int id) {
        repository.deleteById(id);
    }

    @Override
    public List<Project> getAllMoreThanBalance(Integer balance) {
        return repository.findAllMoreThanBalance(balance);
    }

    @Override
    public List<Project> getAllProject() {
        return repository.findAll();
    }
}
