package me.jy.lang.io.cs;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author jy
 */
@Slf4j
public class ClientLogHandler extends ClientHandler {

    @Override
    public void doHandle(Socket clientSocket) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            bufferedReader.readLine();
        } catch (IOException e) {
            log.error("Request error! {}", e);
        }
    }
}
