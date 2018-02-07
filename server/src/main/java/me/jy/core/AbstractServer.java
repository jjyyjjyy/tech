package me.jy.core;

import lombok.extern.slf4j.Slf4j;
import me.jy.request.RequestFailureEvent;
import me.jy.request.RequestFailureListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jy
 * @date 2018/02/07
 */
@Slf4j
public abstract class AbstractServer implements Server {

    protected volatile ServerStatus currentStatus = ServerStatus.INIT;

    protected volatile ServerSocket serverSocket;

    protected final int port;

    protected final int backLog = 512;

    protected List<RequestFailureListener> requestFailureListeners = new ArrayList<>(4);

    protected AbstractServer(int port) {
        this.port = port;
    }

    protected AbstractServer(ServerConfig config) {
        this.port = config.getPort();
    }

    @Override
    public void start() {
        log.debug("Start server : port {}", port);
        this.currentStatus = ServerStatus.STARTING;
        try {
            this.serverSocket = new ServerSocket(port, backLog);
            this.currentStatus = ServerStatus.START;
            log.info("Server start : ip={}, port={}", serverSocket.getInetAddress(), serverSocket.getLocalPort());
            handleServerSocket(serverSocket);
        } catch (IOException e) {
            log.error("Server Start error! ", e);
            this.currentStatus = ServerStatus.STOP;
        }
    }

    protected abstract void handleServerSocket(ServerSocket serverSocket);

    public void callRequestFailureListeners(RequestFailureEvent eventObject) {
        if (!this.requestFailureListeners.isEmpty()) {
            this.requestFailureListeners.forEach(listener -> listener.handle(eventObject));
        }
    }

    public void registerRequestFailureListener(RequestFailureListener listener) {
        this.requestFailureListeners.add(listener);
    }

    @Override
    public void stop() {
        if (serverSocket == null) {
            return;
        }
        log.debug("Stop server : {}", serverSocket);
        this.currentStatus = ServerStatus.STOPPING;
        try {
            // shutdown hook here
            serverSocket.close();
            this.currentStatus = ServerStatus.STOP;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ServerStatus getStatus() {
        return this.currentStatus;
    }

    @Override
    public int getPort() {
        return this.port;
    }

    @Override
    public int getBackLog() {
        return backLog;
    }
}
