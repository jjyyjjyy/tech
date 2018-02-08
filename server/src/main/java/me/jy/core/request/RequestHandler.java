package me.jy.core.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @author jy
 * @date 2018/02/08
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Slf4j
public class RequestHandler extends Thread {

    private volatile Socket socket;

    public void run() {
        try {
            if (socket == null) {
                return;
            }
            log.info(socket.toString());
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("HTTP/1.1 200 OK\\n".getBytes(Charset.defaultCharset()));
            outputStream.flush();
            Thread.sleep(1000);
            outputStream.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}