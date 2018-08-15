package me.jy.lang.io.cs;

import lombok.Getter;

import java.util.concurrent.ExecutorService;

/**
 * @author jy
 */
@Getter
public final class ServerFactory {

    private ClientHandler clientHandler;

    private ExecutorService pool;

    public static ServerFactoryBuilder builder() {
        return new ServerFactoryBuilder();
    }

    public static class ServerFactoryBuilder {

        private final ServerFactory sf;

        private ServerFactoryBuilder() {
            this.sf = new ServerFactory();
        }

        public ServerFactoryBuilder pool(ExecutorService executorService) {
            sf.pool = executorService;
            return this;
        }

        public ServerFactoryBuilder handler(ClientHandler handler) {
            sf.clientHandler = handler;
            return this;
        }

        public BioServer build() {
            return new BioServer(sf);
        }

    }
}
