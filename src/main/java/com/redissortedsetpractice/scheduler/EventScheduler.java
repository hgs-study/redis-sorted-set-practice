package com.redissortedsetpractice.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EventScheduler {

    @Scheduled(fixedDelay =  1000)
    private void schedulerTest(){
        System.out.println(" test = " );
    }
}
