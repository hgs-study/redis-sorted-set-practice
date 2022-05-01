package com.redissortedsetpractice.domain;

import com.redissortedsetpractice.constant.Event;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventCount {
    private Event event;
    private int limit;

    private static final int END = 0;

    public EventCount(Event event, int limit) {
        this.event = event;
        this.limit = limit;
    }

    public synchronized void decrease(){
        this.limit--;
    }

    public boolean end(){
        return this.limit == END;
    }
}
