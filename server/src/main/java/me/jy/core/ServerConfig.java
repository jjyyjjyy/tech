package me.jy.core;

import lombok.Data;

/**
 * @author jy
 * @date 2018/02/06
 */
@Data
public class ServerConfig {

    static final int DEFAULT_PORT = 8888;

    private int port;

    public ServerConfig() {
        this.port = DEFAULT_PORT;
    }
}
