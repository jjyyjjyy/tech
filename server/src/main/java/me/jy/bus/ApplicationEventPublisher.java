package me.jy.bus;

import me.jy.core.ApplicationListener;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jy
 * @date 2018/02/08
 */
public class ApplicationEventPublisher {

    private static final List<ApplicationListener<? extends EventObject>> LISTENERS = new ArrayList<>();

    private static final ExecutorService EVENT_EXECUTOR = Executors.newSingleThreadExecutor();

    public static void publishEvent(EventObject eventObject) {
        EVENT_EXECUTOR.execute(() -> LISTENERS.forEach(listener -> listener.handle(eventObject)));
    }

    public static void registerListener(ApplicationListener<? extends EventObject> listener) {
        LISTENERS.add(listener);
    }
}
