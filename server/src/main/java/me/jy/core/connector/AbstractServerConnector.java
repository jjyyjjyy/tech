package me.jy.core.connector;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import me.jy.bus.ApplicationEventPublisher;
import me.jy.bus.event.ServerStartFailureEvent;
import me.jy.core.Connector;
import me.jy.core.Server;
import me.jy.core.server.ServerConfig;
import me.jy.util.IOUtils;

import java.io.IOException;
import java.net.ServerSocket;

import static me.jy.core.Server.ServerStatus.*;

/**
 * Create {@link ServerSocket} and init it,
 * then accept requests
 *
 * @author jy
 * @date 2018/02/07
 */
@Slf4j
public abstract class AbstractServerConnector implements Connector {

    protected volatile Server.ServerStatus currentStatus = INIT;

    protected ServerSocket serverSocket;

    private final int port;

    private int backLog = 1 << 8;

    protected AbstractServerConnector(int port) {
        this.port = port;
    }

    protected AbstractServerConnector(@NonNull ServerConfig config) {
        this.port = config.getPort();
        this.backLog = config.getBackLog();
    }

    public void init() {
        log.debug("Start server : port {}", port);
        this.currentStatus = STARTING;
        try {
            initServer();
            this.currentStatus = START;
            log.info("Server start : ip={}, port={}", serverSocket.getInetAddress(), serverSocket.getLocalPort());
        } catch (IOException e) {
            log.error("Server Start error! ", e);
            ApplicationEventPublisher.publishEvent(new ServerStartFailureEvent(e));
            this.currentStatus = STOP;
            IOUtils.close(serverSocket);
        }
    }

    /**
     * Create ServerSocket instance by sub class,such as http, https.
     */
    protected abstract void initServer() throws IOException;


    public void closeServer() {
        if (serverSocket == null) {
            return;
        }
        log.debug("Stop server : {}", serverSocket);
        this.currentStatus = STOPPING;
        try {
            // shutdown hook here
            serverSocket.close();
            this.currentStatus = STOP;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Server.ServerStatus getStatus() {
        return this.currentStatus;
    }

    @Override
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public int getPort() {
        return this.port;
    }

    public int getBackLog() {
        return this.backLog;
    }
}
