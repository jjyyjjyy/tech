package me.jy.lang.thread;

import java.util.concurrent.ThreadFactory;

/**
 * @author jy
 */
public enum DaemonThreadFactory implements ThreadFactory {

    INSTANCE;

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setDaemon(true);
        return thread;
    }
}
