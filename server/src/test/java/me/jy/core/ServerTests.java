package me.jy.core;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

import static me.jy.core.Server.ServerStatus.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author jy
 * @date 2018/02/06
 */
public class ServerTests {

    volatile CountDownLatch serverLatch = new CountDownLatch(1);


    private void startServer(Server server) {
        new Thread(server::start).start();

    }

    @Test
    public void testStart() throws Exception {
        ServerConfig serverConfig = new ServerConfig();
        Server server = ServerFactory.getSimpleServer(serverConfig);
        assertEquals(Server.ServerStatus.INIT, server.getStatus());
        startServer(server);

        CountDownLatch selfLatch = new CountDownLatch(1);
        new Thread(() -> {
            Socket socket = new Socket();
            try {
                if (server.getStatus() == INIT) {
                    Thread.sleep(500);
                }
                while (server.getStatus() != START) {
                    Thread.sleep(100);
                }
                socket.connect(new InetSocketAddress("localhost", serverConfig.getPort()));
                System.out.println(socket.isConnected());
                socket.close();
                selfLatch.countDown();

            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

        selfLatch.await();
        server.stop();
        serverLatch.countDown();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPort() {
        ServerConfig serverConfig = new ServerConfig();
        serverConfig.setPort(0);
        ServerFactory.getSimpleServer(serverConfig);
    }

    @Test
    public void testStop() throws InterruptedException {
        ServerConfig serverConfig = new ServerConfig();
        Server server = ServerFactory.getSimpleServer(serverConfig);
        startServer(server);
        Thread.sleep(500);
        server.stop();
        assertTrue(Arrays.asList(STOPPING, STOP).contains(server.getStatus()));
    }
}
