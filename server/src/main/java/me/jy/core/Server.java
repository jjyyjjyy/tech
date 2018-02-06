package me.jy.core;

/**
 * @author jy
 * @date 2018/02/06
 */
public interface Server {

    void start();

    void stop();

    ServerStatus getStatus();

    int getPort();

    enum ServerStatus {

        INIT,

        STARTING,

        START,

        STOPPING,

        STOP
    }
}
