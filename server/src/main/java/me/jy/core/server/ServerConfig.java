package me.jy.core.server;

import lombok.Data;

/**
 * @author jy
 * @date 2018/02/06
 */
@Data
public class ServerConfig {

    static final int DEFAULT_PORT = 8888;
    static final int DEFAULT_BACK_LOG = 512;
    static final int DEFAULT_INIT_WORK_THREADS = 200;

    private Integer port = DEFAULT_PORT;

    private Integer backLog = DEFAULT_BACK_LOG;

    private Integer initWorkThreads = DEFAULT_INIT_WORK_THREADS;

    private Boolean https = false;

    private Boolean nio = false;

    public ServerConfig() {
    }
}
