package com.niteshsinha.candid.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@Slf4j
public class CandidClient {

    private WebClient webClient;

    @Autowired
    public CandidClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public void getData() {
        String temp = "C38973?start_date=2020-04-22+10:12:41&end_date=2020-04-26+12:53:31&devices=249861,249863,249865,337506,337510&locations=&api_version=2&format=json";
        String block = webClient.get()
                .uri(temp)
                .headers(httpHeaders -> httpHeaders.setBasicAuth("staraa@symantec.com","<hidden>")) //password not working anymore - 401 error
                .retrieve()
                .bodyToMono(String.class)
                .log()
                .block();

        log.info("Result: {}", block);

    }
}
