package me.jy.io;

import me.jy.io.cs.ClientLogHandler;
import me.jy.io.cs.ClientTimedHandler;
import me.jy.io.cs.ServerFactory;

import java.util.concurrent.Executors;

/**
 * @author jy
 */
public class IOBenchmark {

    public static void main(String[] args) throws Exception {

        /*// BIO, 10T, 0ms
        ServerFactory.builder()
            .pool(Executors.newFixedThreadPool(10))
            .handler(new ClientLogHandler())
            .build()
            .start();*/

        /*// BIO, 10T, 100ms
        ServerFactory.builder()
            .pool(Executors.newFixedThreadPool(10))
            .handler(new ClientTimedHandler(100L, new ClientLogHandler()))
            .build()
            .start();*/

        /*// BIO, 10T, 100ms
        ServerFactory.builder()
            .pool(Executors.newCachedThreadPool())
            .handler(new ClientTimedHandler(100L, new ClientLogHandler()))
            .build()
            .start();*/

        // BIO, 10T, 1s
        ServerFactory.builder()
            .pool(Executors.newCachedThreadPool())
            .handler(new ClientTimedHandler(1000L, new ClientLogHandler()))
            .build()
            .start();

    }
}
