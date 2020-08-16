package com.niteshsinha.candid.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CandidService {

    private final DatabaseClient databaseClient;
    private final CandidClient candidClient;

    @Autowired
    public CandidService(DatabaseClient databaseClient, CandidClient candidClient) {
        this.databaseClient = databaseClient;
        this.candidClient = candidClient;
    }

    @Scheduled(
            fixedDelayString = "#{${cynic.statusalertsite.poll.interval.seconds:30}*1000}", // 10 mins
            initialDelayString = "#{${cynic.statusalertsite.poll.initial.delay.seconds:120}*1000}" //2 min
    )
    public void serve() {
        log.info("Polling for Updates from alertsite");
        pollAlertsite();
    }

    private void pollAlertsite() {
        candidClient.getData();
    }
}
