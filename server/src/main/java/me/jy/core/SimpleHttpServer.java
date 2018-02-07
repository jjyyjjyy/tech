package me.jy.core;

import lombok.extern.slf4j.Slf4j;
import me.jy.request.RequestFailureEvent;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author jy
 * @date 2018/02/06
 */
@Slf4j
public class SimpleHttpServer extends AbstractServer {

    private final Queue<Thread> workers = new ArrayBlockingQueue<>(200);

    private final Queue<Socket> waitSocket = new LinkedBlockingDeque<>(getBackLog());

    {
        for (int i = 0; i < 200; i++) {
            workers.offer(new RequestHandler());
        }
    }

    public SimpleHttpServer(int port) {
        super(port);
    }

    public SimpleHttpServer(ServerConfig config) {
        super(config);
    }

    @Override
    protected void handleServerSocket(ServerSocket serverSocket) {
        while (this.currentStatus != ServerStatus.STOP) {
            try {
                System.out.println(this.currentStatus);
                Socket clientSocket = serverSocket.accept();
                log.info(clientSocket.toString());
                if (!workers.isEmpty()) {
                    RequestHandler thread = (RequestHandler) workers.poll();
                    if (thread != null) {
                        thread.setSocket(clientSocket);
                        thread.start();
                    }
                } else {
                    waitSocket.offer(clientSocket);
                }
            } catch (Exception e) {
                callRequestFailureListeners(new RequestFailureEvent(e.getMessage()));
            }finally {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
