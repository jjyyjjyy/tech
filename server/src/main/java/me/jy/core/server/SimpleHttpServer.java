package me.jy.core.server;

import lombok.extern.slf4j.Slf4j;
import me.jy.bus.ApplicationEventPublisher;
import me.jy.bus.event.RequestFailureEvent;
import me.jy.core.Server;
import me.jy.core.connector.AbstractServerConnector;
import me.jy.core.request.RequestHandler;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import static me.jy.core.Server.ServerStatus.START;

/**
 * @author jy
 * @date 2018/02/06
 */
@Slf4j
public class SimpleHttpServer implements Server {

    private final AbstractServerConnector connector;

    private final Queue<Thread> workers = new ArrayBlockingQueue<>(200);

    private final Queue<Socket> waitingSockets;

    public SimpleHttpServer(final AbstractServerConnector abstractServerConnector) {
        this.connector = abstractServerConnector;
        int backLog = abstractServerConnector.getBackLog();
        for (int i = 0; i < backLog; i++) {
            this.workers.offer(new RequestHandler());
        }
        waitingSockets = new LinkedBlockingDeque<>(backLog);
    }

    @Override
    public void start() {
        if (connector.getStatus().getOrder() < 2) {
            connector.init();
            if (connector.getStatus() == START) {
                this.handleServerSocket(connector.getServerSocket());
            }
        } else {
            log.info("Server has already started!");
        }
    }

    @Override
    public void stop() {
        connector.closeServer();
    }

    public void handleServerSocket(ServerSocket serverSocket) {
        while (this.getStatus() == START) {
            try {
                Socket clientSocket = serverSocket.accept();
                log.info(clientSocket.toString());
                if (!workers.isEmpty()) {
                    RequestHandler thread = (RequestHandler) workers.poll();
                    if (thread != null) {
                        thread.setSocket(clientSocket);
                        thread.start();
                    }
                } else {
                    waitingSockets.offer(clientSocket);
                }
            } catch (Exception e) {
                ApplicationEventPublisher.publishEvent(new RequestFailureEvent(e.getMessage()));
            } finally {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    public ServerStatus getStatus() {
        return this.connector.getStatus();
    }

    @Override
    public int getPort() {
        return this.connector.getPort();
    }

    @Override
    public int getBackLog() {
        return this.connector.getBackLog();
    }
}
