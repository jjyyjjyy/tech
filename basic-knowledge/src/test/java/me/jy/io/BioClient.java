package me.jy.io;

import lombok.SneakyThrows;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * @author jy
 */
public class BioClient {

    @SneakyThrows
    public static void main(String[] args) {
        Socket socket = new Socket();

//        socket.setSoLinger(false, 0);
//         socket.setSoLinger(true, 0);
        socket.setSoLinger(true, 1);

        SocketAddress address = new InetSocketAddress(9000);
        socket.connect(address);

        OutputStream output = socket.getOutputStream();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20_000; i++) {
            sb.append("hel");
        }
        byte[] request = sb.toString().getBytes(StandardCharsets.UTF_8);
        output.write(request);
        long start = System.currentTimeMillis();
        socket.close();
        long end = System.currentTimeMillis();
        System.out.println("close time cost: " + (end - start));
    }
}
