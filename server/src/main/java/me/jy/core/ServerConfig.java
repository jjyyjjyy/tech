package me.jy.core;

import lombok.Data;

/**
 * @author jy
 * @date 2018/02/06
 */
@Data
public class ServerConfig {

    static final int DEFAULT_PORT = 8888;
    static final int DEFAULT_INIT_WORK_THREADS = 200;

    private int port = DEFAULT_PORT;

    private int initWorkThreads = DEFAULT_INIT_WORK_THREADS;

    public ServerConfig() {
    }
}
