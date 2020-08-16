package com.niteshsinha.candid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CandidApplication {

    public static void main(String[] args) {
        SpringApplication.run(CandidApplication.class, args);
    }

}
