package me.jy.core;

import lombok.extern.slf4j.Slf4j;
import me.jy.util.FileUtils;

import java.util.Properties;

/**
 * @author jy
 * @date 2018/02/06
 */
@Slf4j
public abstract class ServerFactory {

    private static final String CONFIG_FILENAME = "server.properties";
    private static final ServerConfig SERVER_CONFIG;

    static {
        SERVER_CONFIG = new ServerConfig();

        Properties fileProperties = FileUtils.getFileProperties(CONFIG_FILENAME);
        SERVER_CONFIG.setPort(
                FileUtils.getValue(fileProperties, "port", ServerConfig.DEFAULT_PORT, Integer.class));
    }


    public static Server getSimpleServer(ServerConfig config) {
        preCheck(config);

        return new SimpleHttpServer(config);
    }

    private static void preCheck(ServerConfig config) {
        int port = config.getPort();
        if (port == 0 || port >>> 16 != 0) {
            throw new IllegalArgumentException("Invalid bind port!");
        }

        log.debug("Init server: {}", config);
    }
}
