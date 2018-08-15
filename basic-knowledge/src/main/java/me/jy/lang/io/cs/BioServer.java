package me.jy.lang.io.cs;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jy
 */
@Slf4j
public class BioServer {

    private static final int DEFAULT_PORT = 8080;
    protected final ServerSocket serverSocket;
    protected ExecutorService threadPool = Executors.newCachedThreadPool();
    private byte[] MESSAGE = ("HTTP/1.1 200 OK\n" +
        "Date: Sat, 31 Dec 2005 23:59:59 GMT\n" +
        "Content-Type: text/html;charset=ISO-8859-1\n" +
        "Content-Length: 122\n" +
        "\n" +
        "＜html＞\n" +
        "＜head＞\n" +
        "＜title＞Wrox Homepage＜/title＞\n" +
        "＜/head＞\n" +
        "＜body＞\n" +
        "＜!-- body goes here --＞\n" +
        "＜/body＞\n" +
        "＜/html＞").getBytes(Charset.defaultCharset());
    private ClientHandler clientHandler;

    public BioServer() {
        try {
            this.serverSocket = new ServerSocket(DEFAULT_PORT);
            this.clientHandler = new ClientLogHandler();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public BioServer(ServerFactory serverFactory) {
        try {
            this.serverSocket = new ServerSocket();
            this.clientHandler = serverFactory.getClientHandler();
            this.threadPool = serverFactory.getPool();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void start() throws Exception {
        ExecutorService pool = getThreadPool();
        while (true) {
            Socket client = serverSocket.accept();
//            log.debug("Request coming: {}", client);
            OutputStream outputStream = client.getOutputStream();
            pool.execute(() -> handleClient(client));
            outputStream.write(MESSAGE);
            outputStream.close();
//            log.debug("Client closed : {}", client);
            client.close();
        }
    }

    public void close() throws IOException {
        if (serverSocket != null) {
            serverSocket.close();
        }
    }

    public void handleClient(Socket client) {
        clientHandler.handle(client);
    }

    public BioServer use(ClientHandler clientHandler) {
        if (clientHandler != null) {
            this.clientHandler = clientHandler;
        }
        return this;
    }

    public BioServer in(ExecutorService es) {
        if (es != null) {
            this.threadPool = es;
        }
        return this;
    }

    protected ExecutorService getThreadPool() {
        return threadPool;
    }

}
