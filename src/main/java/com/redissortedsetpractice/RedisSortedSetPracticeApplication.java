package com.redissortedsetpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RedisSortedSetPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisSortedSetPracticeApplication.class, args);
    }

}
