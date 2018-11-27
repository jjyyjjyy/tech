package me.jy._2_factory_method;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author jy
 */
public class FileLogger implements Logger {

    private final BufferedWriter FILE_WRITER;

    public FileLogger(String file) throws IOException {
        FILE_WRITER = new BufferedWriter(new FileWriter(file));
    }

    @Override
    public void log(String message) {
        try {
            FILE_WRITER.write(message);
            FILE_WRITER.newLine();
            FILE_WRITER.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
