package com.jnlim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TennisReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(TennisReservationApplication.class, args);
    }

}
