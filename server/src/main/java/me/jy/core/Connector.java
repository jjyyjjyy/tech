package me.jy.core;

import java.net.ServerSocket;

/**
 * @author jy
 * @date 2018/02/08
 */
public interface Connector {

    void init();

    void closeServer();

    ServerSocket getServerSocket();
}
