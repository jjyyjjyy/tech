package me.jy.core;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jy
 * @date 2018/02/06
 */
@Slf4j
public class SimpleHttpServer implements Server {

    private volatile ServerStatus currentStatus = ServerStatus.INIT;

    private final int port;

    public SimpleHttpServer(int port) {
        this.port = port;
    }

    public SimpleHttpServer(ServerConfig config) {
        this.port = config.getPort();
    }

    @Override
    public void start() {
        log.debug("Start server : port {}", port);
        this.currentStatus = ServerStatus.STARTING;
    }

    @Override
    public void stop() {
        log.debug("Stop server : port {}", port);
        this.currentStatus = ServerStatus.STOPPING;
    }

    @Override
    public ServerStatus getStatus() {
        return this.currentStatus;
    }

    @Override
    public int getPort() {
        return this.port;
    }
}
