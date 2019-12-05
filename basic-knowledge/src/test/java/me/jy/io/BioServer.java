package me.jy.io;

import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * nc localhost 9000
 * Welcome the server!
 * <p>
 * send 20bytes, receive 10bytes.
 *
 * @author jy
 */
public class BioServer {

    @SneakyThrows
    public static void main(String[] args) {
        @Cleanup ServerSocket serverSocket = new ServerSocket();
//        serverSocket.setReuseAddress(false);
        serverSocket.bind(new InetSocketAddress(9000));
        while (true) {
            @Cleanup Socket socket = serverSocket.accept();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            System.out.println(bufferedReader.readLine());
            socket.getOutputStream().write("Thank you!".getBytes());
        }
    }
}
