package com.eaziche.zoo.utils;

import com.squareup.otto.Bus;

/**
 * Created by hardik on 23-11-2016.
 */
public class EventBus extends Bus {
    private static EventBus ourInstance = new EventBus();

    public static EventBus getInstance() {
        return ourInstance;
    }

    private EventBus() {
    }
}
