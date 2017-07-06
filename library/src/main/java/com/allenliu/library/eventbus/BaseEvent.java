package com.allenliu.library.eventbus;

/**
 * Created by Allen Liu on 2017/05/26.
 */

public class BaseEvent {
    private int eventType;

    public int getEventType() {
        return eventType;
    }

    public BaseEvent setEventType(int eventType) {
        this.eventType = eventType;
        return this;
    }
}
