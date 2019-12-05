package me.jy.io.cs;

import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * @author jy
 */
public class ClientTimedHandler extends ClientHandler {

    private final long sleepTime;

    public ClientTimedHandler() {
        this.sleepTime = 1L;
    }

    public ClientTimedHandler(long sleepTime) {
        this.sleepTime = sleepTime;
    }

    public ClientTimedHandler(long sleepTime, ClientHandler delegate) {
        super(delegate);
        this.sleepTime = sleepTime;
    }

    public ClientTimedHandler(ClientHandler delegate) {
        super(delegate);
        this.sleepTime = 1L;
    }

    public void doHandle(Socket clientSocket) {
        try {
            TimeUnit.MILLISECONDS.sleep(sleepTime);
        } catch (InterruptedException ignore) {
        }
    }
}
