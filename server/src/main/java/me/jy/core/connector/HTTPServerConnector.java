package me.jy.core.connector;

import me.jy.core.server.ServerConfig;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @author jy
 * @date 2018/02/08
 */
public class HTTPServerConnector extends AbstractServerConnector {

    public HTTPServerConnector(int port) {
        super(port);
    }

    public HTTPServerConnector(ServerConfig config) {
        super(config);
    }

    @Override
    public void initServer() throws IOException {
        serverSocket = new ServerSocket(getPort(), getBackLog());
    }

}
