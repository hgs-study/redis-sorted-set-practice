package com.redissortedsetpractice.service;

import com.redissortedsetpractice.constant.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class GifticonServiceTest {

    @Autowired
    private GifticonService gifticonService;

    @Test
    void 선착순이벤트_100명에게_기프티콘_30개_제공() throws InterruptedException {
        final Event chickenEvent = Event.CHICKEN;
        final int people = 100;
        final int limitCount = 30;
        final CountDownLatch countDownLatch = new CountDownLatch(people);
        gifticonService.setEventCount(chickenEvent, limitCount);

        List<Thread> workers = Stream
                                .generate(() -> new Thread(new AddQueueWorker(countDownLatch, chickenEvent)))
                                .limit(people)
                                .collect(Collectors.toList());
        workers.forEach(Thread::start);
        countDownLatch.await();
        Thread.sleep(5000); // 기프티콘 발급 스케줄러 작업 시간

        final long failEventPeople = gifticonService.getSize(chickenEvent);
        assertEquals(people - limitCount, failEventPeople); // output : 70 = 100 - 30
    }

    private class AddQueueWorker implements Runnable{
        private CountDownLatch countDownLatch;
        private Event event;

        public AddQueueWorker(CountDownLatch countDownLatch, Event event) {
            this.countDownLatch = countDownLatch;
            this.event = event;
        }

        @Override
        public void run() {
            gifticonService.addQueue(event);
            countDownLatch.countDown();
        }
    }
}