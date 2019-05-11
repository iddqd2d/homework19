package com.homework18.service;

import com.homework18.model.Project;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SchedulerService {
    private static final String CRON = "*/10 * * * * *";

    private final ProjectRepositoryService projectService;
    private final EmailService emailService;


    @Scheduled(cron = CRON)
    public void sendMailToUsers() {
        List<Project> list = projectService.getAllMoreThanBalance(5000);
        if (!list.isEmpty()) {
            list.forEach(project -> {
                try {
                    String message = "Deer " + project.getName() + "you should raise your developers salary ";
                    emailService.send(project.getEmail(), "Attention!", message);
                } catch (Exception e) {
                    log.error("Email can't be sent.Project's id: {}, Error: {}", project.getId(), e.getMessage());
                    log.error("Email can't be sent", e);
                }
            });
        }
    }
}
