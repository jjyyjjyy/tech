package me.jy.core.server;

import lombok.extern.slf4j.Slf4j;
import me.jy.bus.ApplicationEventPublisher;
import me.jy.bus.DefaultRequestFailureListener;
import me.jy.bus.event.DefaultSeverFailureListener;
import me.jy.core.Server;
import me.jy.core.connector.HTTPServerConnector;
import me.jy.util.FileUtils;

/**
 * @author jy
 * @date 2018/02/06
 */
@Slf4j
public abstract class ServerFactory {

    private static final String CONFIG_FILENAME = "server.properties";
    private static final ServerConfig SERVER_CONFIG;

    static {
        SERVER_CONFIG = FileUtils.convertFileToObject(ServerConfig.class, FileUtils.getFileProperties(CONFIG_FILENAME));

        ApplicationEventPublisher.registerListener(new DefaultSeverFailureListener());
        ApplicationEventPublisher.registerListener(new DefaultRequestFailureListener());
    }


    public static Server getSimpleServer() {
        HTTPServerConnector connector = new HTTPServerConnector(SERVER_CONFIG);
        return new SimpleHttpServer(connector);
    }

    public static Server getSimpleServer(ServerConfig config) {
        preCheck(config);

        HTTPServerConnector connector = new HTTPServerConnector(SERVER_CONFIG);
        return new SimpleHttpServer(connector);
    }

    private static void preCheck(ServerConfig config) {
        int port = config.getPort();
        if (port == 0 || port >>> 16 != 0) {
            throw new IllegalArgumentException("Invalid bind port!");
        }

        log.debug("Init server: {}", config);
    }
}
