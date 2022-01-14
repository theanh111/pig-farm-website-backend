package com.school.core.service;

import com.school.core.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTask {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Scheduled(initialDelay = 178000, fixedDelay = 178000)
    public void keepAlive() {
        scheduleRepository.keepAlive();
    }
}
