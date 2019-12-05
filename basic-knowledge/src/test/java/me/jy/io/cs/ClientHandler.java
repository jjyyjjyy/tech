package me.jy.io.cs;

import java.net.Socket;

/**
 * @author jy
 */
public abstract class ClientHandler {

    private final ClientHandler delegate;

    public ClientHandler() {
        this.delegate = null;
    }

    protected ClientHandler(ClientHandler delegate) {
        this.delegate = delegate;
    }

    public void handle(Socket clientSocket) {
        if (this.delegate != null) {
            delegate.handle(clientSocket);
        }
    }

    protected abstract void doHandle(Socket clientSocket);
}
