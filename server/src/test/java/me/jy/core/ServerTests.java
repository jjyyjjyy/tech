package me.jy.core;

import me.jy.core.server.ServerConfig;
import me.jy.core.server.ServerFactory;
import org.junit.Test;

import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Arrays;

import static me.jy.core.Server.ServerStatus.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author jy
 * @date 2018/02/06
 */
public class ServerTests {

    private void startServer(Server server) {
        new Thread(server::start).start();
    }

    @Test
    public void testStart() throws Exception {
        ServerConfig serverConfig = new ServerConfig();
        serverConfig.setPort(8801);
        Server server = ServerFactory.getSimpleServer(serverConfig);
        assertEquals(Server.ServerStatus.INIT, server.getStatus());
        startServer(server);


        while (true) {
            if (!(server.getStatus() == START)) continue;
            Thread.sleep(200);
            Socket socket = new Socket("localhost", serverConfig.getPort());
            socket.getInputStream();
            socket.getOutputStream().write("HTTP/1.1 GET".getBytes(Charset.defaultCharset()));

            assertTrue(socket.getInputStream().available() > 0);
            server.stop();
            break;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPort() {
        ServerConfig serverConfig = new ServerConfig();
        serverConfig.setPort(0);
        ServerFactory.getSimpleServer(serverConfig).start();
    }

    @Test
    public void testStop() throws InterruptedException {
        Server server = ServerFactory.getSimpleServer();
        startServer(server);
        Thread.sleep(500);
        server.stop();
        assertTrue(Arrays.asList(STOPPING, STOP).contains(server.getStatus()));
    }
}
