package me.jy.core;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author jy
 * @date 2018/02/06
 */
public class ServerTests {

    @Test
    public void testStart() {
        ServerConfig serverConfig = new ServerConfig();
        Server server = ServerFactory.getSimpleServer(serverConfig);
        assertEquals(Server.ServerStatus.INIT, server.getStatus());
        server.start();
        assertEquals(Server.ServerStatus.STARTING, server.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPort() {
        ServerConfig serverConfig = new ServerConfig();
        serverConfig.setPort(0);
        ServerFactory.getSimpleServer(serverConfig);
    }

    @Test
    public void testStop() {
        ServerConfig serverConfig = new ServerConfig();
        Server server = ServerFactory.getSimpleServer(serverConfig);
        server.stop();
        assertEquals(Server.ServerStatus.STOPPING, server.getStatus());
    }
}
