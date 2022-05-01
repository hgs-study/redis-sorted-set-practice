package com.redissortedsetpractice.service;

import com.redissortedsetpractice.constant.Event;
import com.redissortedsetpractice.domain.EventCount;
import com.redissortedsetpractice.domain.Gifticon;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class GifticonService {
    private final RedisTemplate<String,Object> redisTemplate;
    private static final long FIRST_ELEMENT = 0;
    private static final long LAST_ELEMENT = -1;
    private static final long PUBLISH_QUEUE = 10;
    private static final long DECREASE_INDEX = 1;
    private EventCount eventCount;

    public void setEventCount(Event event, int queue){
        this.eventCount = new EventCount(event, queue);
    }

    public void addQueue(Event event){
        final long now = System.currentTimeMillis();
        final String people = Thread.currentThread().getName();

        redisTemplate.opsForZSet().add(event.toString(), people, now);
        log.info("대기열에 추가 - {} ({}초)", people, now);
    }

    public void getOrder(Event event){
        final long start = FIRST_ELEMENT;
        final long end = LAST_ELEMENT;

        Set<Object> queue = redisTemplate.opsForZSet().range(event.toString(), start, end);

        for (Object people : queue) {
            Long rank = redisTemplate.opsForZSet().rank(event.toString(), people);
            log.info("'{}'님의 현재 대기열은 {}명 남았습니다.", people, rank);
        }
    }

    public void publish(Event event){
        final long start = FIRST_ELEMENT;
        final long end = PUBLISH_QUEUE - DECREASE_INDEX;

        Set<Object> queue = redisTemplate.opsForZSet().range(event.toString(), start, end);
        for (Object people : queue) {
            final Gifticon gifticon = new Gifticon(event);
            log.info("'{}'님의 {} 기프티콘이 발급되었습니다 ({})",people, gifticon.getEvent().getName(), gifticon.getCode());
            redisTemplate.opsForZSet().remove(event.toString(), people);
            this.eventCount.decrease();
        }
    }

    public boolean validEnd(){
        return this.eventCount.end();
    }

    public long getSize(Event event){
        return redisTemplate.opsForZSet().size(event.toString());
    }

}
