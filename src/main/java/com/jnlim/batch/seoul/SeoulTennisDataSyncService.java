package com.jnlim.batch.seoul;

import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class SeoulTennisDataSyncService {
    private final ExecutorService executorService = Executors.newFixedThreadPool(1);

    private final SeoulTennisDataSyncTask seoulTennisDataSyncTask;

    public SeoulTennisDataSyncService(SeoulTennisDataSyncTask seoulTennisDataSyncTask) {
        this.seoulTennisDataSyncTask = seoulTennisDataSyncTask;
    }

    @Scheduled(cron = "0 0/30 * * * *")
    public void execute() {
        executorService.execute(seoulTennisDataSyncTask);
    }

    @PreDestroy
    public void shutdown() {
        try {
            if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                executorService.shutdown();
            }
        } catch (InterruptedException e) {
            executorService.shutdown();
            Thread.currentThread().interrupt();
        }
    }
}
