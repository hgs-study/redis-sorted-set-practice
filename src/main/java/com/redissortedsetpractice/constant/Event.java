package com.redissortedsetpractice.constant;

import lombok.Getter;

@Getter
public enum Event {
    CHICKEN("치킨");

    private String name;

    Event(String name) {
        this.name = name;
    }
}
