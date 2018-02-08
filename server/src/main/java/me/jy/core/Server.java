package me.jy.core;

import java.net.ServerSocket;

/**
 * @author jy
 * @date 2018/02/06
 */
public interface Server extends LifeCycle {

    ServerStatus getStatus();

    int getPort();

    int getBackLog();

    void handleServerSocket(ServerSocket serverSocket);

    enum ServerStatus {

        INIT(0),

        STARTING(1),

        START(2),

        STOPPING(3),

        STOP(4);

        private final int order;

        ServerStatus(int order) {
            this.order = order;
        }

        public int getOrder() {
            return order;
        }
    }
}
